<template>
	<view>
		软件一班
		{{getDictDataName(value)}}
	</view>
</template>

<script>
export default {
  name: "DictTag",
  props: {
    options: {
      type: Array,
      default: null,
    },
    value: [Number, String, Array],
    // 当未找到匹配的数据时，显示value
    showValue: {
      type: Boolean,
      default: true,
    },
    separator: {
      type: String,
      default: ","
    }
  },
  data() {
    return {
      
    }
  },
  methods:{
	  getDictDataName(value){
		  console.log(value)
		  this.options.forEach(item=>{
			  console.log(item)
			  if(parseInt(item.dictValue)==this.value){
				  console.log(11)
				  return item.dictLabel
			  }
		  })
	  }
  }
  }
</script>
<style scoped>

</style>
