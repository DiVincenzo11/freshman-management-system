<template>
  <div class="app-container home">
    <h2>新生信息与跟进管理系统</h2>
    <p class="intro">围绕学生档案、关注标记和沟通记录，整理新生入学管理流程。</p>
    <el-alert title="公开演示版本：所有学生、班级、联系方式和跟进内容均为虚构数据。" type="info" :closable="false" show-icon />
    <el-row :gutter="24" class="cards">
      <el-col :sm="8" :xs="24"><el-card><i class="el-icon-user"/><h3>学生档案</h3><p>按班级、姓名、宿舍和关注情况筛选，支持 Excel 导入导出。</p><el-button type="primary" @click="$router.push('/freshman/student')">进入学生档案</el-button></el-card></el-col>
      <el-col :sm="8" :xs="24"><el-card><i class="el-icon-collection-tag"/><h3>关注标记</h3><p>用关注类型归纳学生需求，在学生详情中添加与维护标记。</p></el-card></el-col>
      <el-col :sm="8" :xs="24"><el-card><i class="el-icon-chat-line-square"/><h3>持续跟进</h3><p>在学生详情中查看沟通记录、添加文字和图片，自动更新跟进状态。</p></el-card></el-col>
    </el-row>
    <p class="intro">技术基础：RuoYi-Vue · Spring Boot · Vue 2 · MyBatis · MySQL · Redis</p>
  </div>
</template>
<script>export default { name: 'Index' }</script>
<style scoped>.home{padding:32px}.intro{color:#667085;line-height:1.8}.cards{margin-top:28px}.el-card{min-height:260px;margin-bottom:20px}.el-card i{font-size:32px;color:#409eff}.el-card p{line-height:1.8;color:#667085}</style>
