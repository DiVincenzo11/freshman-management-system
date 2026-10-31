<template>
	<view class="bg-white " style="height: 100%;">
		<uni-section title="跟进记录" type="line">
			<uni-card :title="item.createTime" v-for="item in followList">
				<!-- <image slot='cover' style="width: 100%;" :src="cover"></image> -->
				<view class="uni-body">
					<view>{{item.followContent}}</view>
					<view class="flex">
						<uv-image width="80px" height="80px" v-for="item in item.followImage"
							:src="baseUrl+item"></uv-image>
					</view>
				</view>
				<view   slot="actions" class="card-actions" >
					<view v-if="checkPermi(['system:follow_up:remove'])"  class="card-actions-item" @click="handleDelete(item.followId)">
						<uni-icons type="trash" size="18" color="#999"></uni-icons>
						<text class="card-actions-item-text">删除</text>
					</view>
					<view @click="handleEdit(item.followId)" v-if="checkPermi(['system:follow_up:edit'])"  class="card-actions-item">
						<uni-icons type="gear" size="18" color="#999"></uni-icons>
						<text class="card-actions-item-text">编辑</text>
					</view>
				</view>
			</uni-card>
		</uni-section>
		<view style="padding-bottom: 50px;"></view>
		<view  v-if="checkPermi(['system:follow_up:add'])" class="footer">
			<button size="default" type="primary" @click="handleAdd">新增</button>
		</view>
		<!-- 新增 -->
		<uv-modal style="width: 100%;" :showCancelButton="true" @close="close" ref="modal" title="新增跟进记录" @confirm="submit">
			<view>
				<uni-forms class="p10"  ref="stuform" v-model="followForm"
					label-width="50">
					<uni-forms-item label="内容" required name="followContent">
						<uni-easyinput type="textarea" v-model="followForm.followContent" placeholder="请输入跟进内容" />
					</uni-forms-item>
					<uni-forms-item label="附件" name="followImage">
						<uv-upload :fileList="photoList" name="photo" :maxCount="5" @afterRead="afterRead"
							@delete="deletePic" width="100" height="100" />
					</uni-forms-item>
				</uni-forms>
			</view>
		</uv-modal>
		
		<!-- 编辑 -->
		<uv-modal style="width: 100%;" :showCancelButton="true" @close="close" ref="modalUpdate" title="编辑跟进记录" @confirm="submitEdit">
			<view>
				<uni-forms class="p10"  ref="stuform" v-model="followForm"
					label-width="50">
					<uni-forms-item label="内容" required name="followContent">
						<uni-easyinput type="textarea" v-model="followForm.followContent" placeholder="请输入跟进内容" />
					</uni-forms-item>
					<uni-forms-item label="附件" name="followImage">
						<uv-upload :fileList="photoList" name="photo" :maxCount="5" @afterRead="afterRead"
							@delete="deletePic" width="100" height="100" />
					</uni-forms-item>
				</uni-forms>
			</view>
		</uv-modal>
	</view>
</template>

<script>
	import {
		listFollow_up,
		delFollow_up,
		addFollow_up,
		updateFollow_up,
		getFollow_up
	} from '@/api/system/follow_up.js'
	import {
		getToken,
		setToken,
		removeToken
	} from '@/utils/auth'
	import {
		baseUrl
	} from '../../../config'
	import { checkPermi,checkRole} from "@/utils/permission";
	export default {
		data() {
			return {
				studentId: null,
				followList: [],
				baseUrl: baseUrl,
				followForm: {
					followContent: '',
					followImage: '',
					studentId:null
				},
				photoList: []
			}
		},
		onLoad(e) {
			this.studentId = e.studentId
			this.followForm.studentId=e.studentId
			this.getList()
		},
		methods: {
			checkPermi,
			checkRole,
			close(){
				// this.reset()
			},
			async submit() {
				if (this.followForm.followContent == '') {
					this.$modal.showToast("内容不能为空")
					return
				}
				await this.clearPhoto()
				console.log("222",this.followForm)
				addFollow_up(this.followForm).then(res => {
					this.$modal.showToast(res.msg)
					this.getList()
					this.reset()
				})
			},
			async submitEdit() {
				if (this.followForm.followContent == '') {
					this.$modal.showToast("内容不能为空")
					return
				}
				await this.clearPhoto()
				uni.hideLoading()
				updateFollow_up(this.followForm).then(res => {
					this.$modal.showToast(res.msg)
					this.getList()
					this.reset()
				})
			},
			handleEdit(followId){
				var that=this
				this.photoList=[]
				getFollow_up(followId).then(res=>{
					this.followForm.followContent=res.data.followContent
					this.followForm.followId=res.data.followId
					var followImage=res.data.followImage
					if(followImage!=null&&followImage!=""){
						followImage=followImage.split(",")
						followImage.forEach(item=>{
							that.photoList.push(
								{url:baseUrl+item}
							)
						})
					}
					this.$refs.modalUpdate.open()
				})
			},
			reset() {
				this.followForm.followContent=''
				this.followForm.followImage=''
				this.photoList=[]
			},
			deletePic(event) {
				console.log(event)
				this.photoList.splice(event.index, 1)
			},
			clearPhoto() {
				uni.showLoading({

				})
				return new Promise(res => {
					let resultObject = {
						urls: this.photoList.map(item => item.url)
					};
					console.log(resultObject.urls.join())
					this.followForm.followImage = resultObject.urls.join()
					console.log(this.followForm)
					this.followForm.followImage = this.followForm.followImage.replace(new RegExp(baseUrl, 'g'), "");
					console.log("follow",this.followForm)
					setTimeout(() => {
						uni.hideLoading()
						res()
					}, 500)
				})
			},
			// 新增图片
			async afterRead(event) {
				const photo = await this.uploadFilePromise(event.file.url)
				this.photoList.push({
					url: baseUrl + photo
				})

			},
			uploadFilePromise(url) {
				console.log(getToken())
				return new Promise((resolve, reject) => {
					let a = uni.uploadFile({
						url: baseUrl + '/common/upload', // 仅为示例，非真实的接口地址
						filePath: url,
						name: 'file',
						formData: {
							user: 'test'
						},
						header: {
							Authorization: "Bearer " + getToken(),
						},
						success: (res) => {
							console.log(res)
							let jsonObject = JSON.parse(res.data)
							setTimeout(() => {
								resolve(jsonObject.fileName)
							}, 1000)
						}
					});
				})
			},
			handleAdd() {
				this.$refs.modal.open()
			},
			handleDelete(id) {
				var that = this
				uni.showModal({
					title: '提示',
					content: '是否确认删除',
					success: function(res) {
						if (res.confirm) {
							delFollow_up(id).then(res => {
								that.$modal.showToast(res.msg)
								that.getList()
							})
						}
						if (res.cancel) {
							that.$modal.showToast('已取消')
						}
					}
				})
			},
			getList() {
				listFollow_up({
					studentId: this.studentId
				}).then(res => {

					this.followList = res.rows
					for (var i = 0; i < this.followList.length; i++) {
						if (this.followList[i].followImage != null&&this.followList[i].followImage != "") {
							this.followList[i].followImage = this.followList[i].followImage.split(",")
						}
					}
					console.log(this.followList)
				})
			}
		}
	}
</script>

<style lang="scss">
	page {
		height: 100%;
	}
	::v-deep .uni-forms-item{
		width: 280px;
	}
	.footer {
		position: fixed;
		bottom: 0px;
		height: 50px;
		width: 100%;
	}

	::v-deep .uni-card {
		span {
			font-size: 12px;
		}
	}

	.follow_content {
		padding-top: 20px;
		padding-left: 30px;
	}

	.container {
		overflow: hidden;
	}

	.custom-cover {
		flex: 1;
		flex-direction: row;
		position: relative;
	}

	.cover-content {
		position: absolute;
		bottom: 0;
		left: 0;
		right: 0;
		height: 40px;
		background-color: rgba($color: #000000, $alpha: 0.4);
		display: flex;
		flex-direction: row;
		align-items: center;
		padding-left: 15px;
		font-size: 14px;
		color: #fff;
	}

	.card-actions {
		display: flex;
		flex-direction: row;
		justify-content: space-around;
		align-items: center;
		height: 45px;
		border-top: 1px #eee solid;
	}

	.card-actions-item {
		display: flex;
		flex-direction: row;
		align-items: center;
	}

	.card-actions-item-text {
		font-size: 12px;
		color: #666;
		margin-left: 5px;
	}

	.cover-image {
		flex: 1;
		height: 150px;
	}

	.no-border {
		border-width: 0;
	}
</style>