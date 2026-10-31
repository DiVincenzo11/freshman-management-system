<template>
	<div class="login">
		<div class="flex" style="display: flex;width: 100%;padding: 5px;">
			<img  style="width: 100px;padding: 5px;" src="../assets/images/logo1.png" />
			<img  style="width: 100px;padding: 5px;" src="../assets/images/logo2.png" />
		</div>
		<div class="login_content">
			<div>
				<div class="center">
					<img style="width: 300px" src="../assets/images/bg1.png" />
				</div>
				<div class="center" style="margin-top: 70px;">
					<img style="width: 400px;" src="../assets/images/title1.jpg" />
				</div>
				<div style="display: flex;justify-content: center;margin-top: 5%;">
					<el-form ref="loginForm" :model="studentFrom" :rules="loginRules" class="login-form">
						<el-form-item prop="studentName">
							<el-input v-model="studentFrom.studentName" type="text" auto-complete="off"
								placeholder="请输入您的姓名">
								<svg-icon slot="prefix" icon-class="user" class="el-input__icon input-icon" />
							</el-input>
						</el-form-item>
						<el-form-item prop="idCard">
							<el-input v-model="studentFrom.idCard" auto-complete="off" placeholder="请输入演示身份编号">
								<svg-icon slot="prefix" icon-class="password" class="el-input__icon input-icon" />
							</el-input>
						</el-form-item>
						<el-form-item style="width:100%;">
							<el-button :loading="loading" size="medium"
								style="width:100%;background: #aa0000;color: #fff;border: 1px solid #aa0000;"
								@click.native.prevent="getInfo">
								<span v-if="!loading">查询 </span>
								<span v-else>查 询 中...</span>
							</el-button>
						</el-form-item>
						<div v-if="dormitory!=null" style="color: #fff;font-weight: 600;text-align: center;">
							您好同学,您的宿舍为{{dormitory}}
						</div>
						<div v-if="dormitory!=null" style="color: #fff;font-weight: 600;text-align: center;margin-top: 8px;">
							欢迎来到数学与信息学院、软件学院!
						</div>
					</el-form>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import {
		getCodeImg
	} from "@/api/login";
	import {
		getdormitory
	} from '@/api/system/student'
	import Cookies from "js-cookie";
	import {
		encrypt,
		decrypt
	} from '@/utils/jsencrypt'

	export default {
		name: "Login",
		data() {
			return {
				codeUrl: "",
				studentFrom: {
					studentName: "",
					idCard: "",
				},
				loginRules: {
					studentName: [{
						required: true,
						trigger: "blur",
						message: "请输入您的姓名"
					}],
					idCard: [{
							required: true,
							trigger: "blur",
							message: "请输入演示身份编号"
						},
						{
							min: 1,
							max: 64,
							message: '请输入 1 至 64 位演示身份编号',
							trigger: 'blur'
						}
					],
				},
				loading: false,
				redirect: undefined,
				dormitory: null
			};
		},
		watch: {},
		created() {},
		methods: {
			getInfo() {
				this.loading = true
				this.$refs.loginForm.validate(valid => {
					if (valid) {
						getdormitory(this.studentFrom).then(res => {
							this.dormitory = res.data.dormitory
							this.loading = false
						}).catch(err => {
							this.dormitory = null
							this.loading = false
						})
					} else {
						this.$modal.msgError("请将信息补充完整");
						this.loading = false
					}
				})

			}
		}
	};
</script>

<style rel="stylesheet/scss" lang="scss">
	.center {
		display: flex;
		justify-content: center;
	}

	.login {
		height: 100%;


		background-image: url("../assets/images/bg.jpg");
		background-size: 100% 100%
	}

	.login_content {
		justify-content: center;
		align-items: center;
		display: flex;

	}

	.title {
		padding-top: 20px;
		text-align: center;
		color: #fff;
		font-size: 24px;
		font-weight: 600;
		color: #fef0db;

	}

	.login-form {
		border-radius: 6px;
		// background: #ffffff;
		width: 400px;
		padding: 25px 25px 5px 25px;

		.el-input {
			height: 38px;

			input {
				height: 38px;
			}
		}

		.input-icon {
			height: 39px;
			width: 14px;
			margin-left: 2px;
		}
	}

	.login-tip {
		font-size: 13px;
		text-align: center;
		color: #bfbfbf;
	}

	.login-code {
		width: 33%;
		height: 38px;
		float: right;

		img {
			cursor: pointer;
			vertical-align: middle;
		}
	}

	.el-login-footer {
		height: 40px;
		line-height: 40px;
		position: fixed;
		bottom: 0;
		width: 100%;
		text-align: center;
		color: #fff;
		font-family: Arial;
		font-size: 12px;
		letter-spacing: 1px;
	}

	.login-code-img {
		height: 38px;
	}
</style>