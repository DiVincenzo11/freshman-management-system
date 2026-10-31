<template>
	<view class="normal-login-container">
		<uni-nav-bar title="数字与信息学院" :border="false" :shadow="true"></uni-nav-bar>

		<view class="logo-content align-center justify-center flex">
			<text class="title">软件学院迎新系统</text>
		</view>
		<view class="login-form-content">
			<view class="input-item flex align-center">
				<view class="iconfont icon-user icon"></view>
				<input v-model="loginForm.studentName" class="input" type="text" placeholder="请输入姓名" maxlength="30" />
			</view>
			<view class="input-item flex align-center">
				<view class="iconfont icon-password icon"></view>
				<input v-model="loginForm.idCard" class="input" placeholder="请输入身份证号" maxlength="20" />
			</view>
			<view class="action-btn">
				<button @click="getInfo" class="login-btn cu-btn block bg-blue lg round">查询</button>
			</view>
		</view>

	</view>
</template>

<script>
	import {
		getdormitory
	} from '@/api/system/student.js'
	export default {
		data() {
			return {
				loginForm: {
					studentName: "",
					idCard: "",
				},
				dormitory:null
			}
		},
		onLoad() {
		},
		methods: {
			getInfo(){
				if(this.loginForm.studentName==''||this.loginForm.studentName==null){
					return this.$modal.showToast("请将姓名补充完整")
				}
				if(this.loginForm.idCard==''||this.loginForm.idCard==null){
					return this.$modal.showToast("请将身份证号补充完整")
				}
				if(this.loginForm.idCard.length!=18){
					return this.$modal.showToast("身份证号的长度必须位18位")
				}
				getdormitory(this.loginForm).then(res => {
					uni.showModal({
						showCancel:false,
						title:'您好同学',
						content:'您的宿舍号为'+res.data.dormitory,
					})
				})
			}

		}
	}
</script>

<style lang="scss">
	page {
		background-color: #ffffff;
	}

	.normal-login-container {
		width: 100%;

		.logo-content {
			width: 100%;
			font-size: 21px;
			text-align: center;
			padding-top: 15%;

			image {
				border-radius: 4px;
			}

			.title {
				margin-left: 10px;
			}
		}

		.login-form-content {
			text-align: center;
			margin: 20px auto;
			margin-top: 15%;
			width: 80%;

			.input-item {
				margin: 20px auto;
				background-color: #f5f6f7;
				height: 45px;
				border-radius: 20px;

				.icon {
					font-size: 38rpx;
					margin-left: 10px;
					color: #999;
				}

				.input {
					width: 100%;
					font-size: 14px;
					line-height: 20px;
					text-align: left;
					padding-left: 15px;
				}

			}

			.login-btn {
				margin-top: 40px;
				height: 45px;
			}

			.reg {
				margin-top: 15px;
			}

			.xieyi {
				color: #333;
				margin-top: 20px;
			}

			.login-code {
				height: 38px;
				float: right;

				.login-code-img {
					height: 38px;
					position: absolute;
					margin-left: 10px;
					width: 200rpx;
				}
			}
		}
	}
</style>