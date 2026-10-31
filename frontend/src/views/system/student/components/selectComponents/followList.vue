<template>
	<div class="block timelineHeight">
		<el-timeline>
			<el-timeline-item v-for="item in list" :timestamp="item.createTime" placement="top">
				<el-card>
					<h4>{{item.followContent}}</h4>
					<div v-if="item.followImage!=null&&item.followImage!=''"><image-preview :src="item.followImage"
							:width="50" :height="50" /></div>
					<div style="display: flex;justify-content: space-between;">
						<div style="color: #808080;width: 300px;">创建时间 {{item.createTime}}</div>
						<div class="ml10">
							<el-button size="mini" type="danger" icon="el-icon-delete" @click="handleDelete(item)"
								v-hasPermi="['system:follow_up:remove']">删除</el-button>
						</div>
					</div>
				</el-card>
			</el-timeline-item>
		</el-timeline>
	</div>
</template>

<script>
	import {
		listFollow_up,
		getFollow_up,
		delFollow_up,
		addFollow_up,
		updateFollow_up
	} from "@/api/system/follow_up";
	export default {
		name: 'followList',
		data() {
			return {
				list: [],
				studentId:null
			}
		},
		methods: {
			getData(studentId) {
				this.studentId=studentId
				listFollow_up({
					studentId: studentId
				}).then(res => {
					this.list = res.rows
				})
			},
			handleDelete(row) {
			  const followIds = row.followId || this.ids;
			  this.$modal.confirm('是否确认删除？').then(function() {
			    return delFollow_up(followIds);
			  }).then(() => {
			    listFollow_up({
			    	studentId: this.studentId
			    }).then(res => {
			    	this.list = res.rows
			    })
			    this.$modal.msgSuccess("删除成功");
			  }).catch(() => {});
			},
		}
	}
</script>

<style>
	.timelineHeight {
		max-height: 500px;
		overflow: auto;
	}
</style>