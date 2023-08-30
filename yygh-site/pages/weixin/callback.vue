<template>
  <!-- header -->
  <div>
  </div>
  <!-- footer -->
</template>

<script>
export default {
  name: "callback",
  data(){
    return{

    }
  },
  mounted() {
    // 得到重定向get请求传过来的参数
    let token = this.$route.query.token
    let name = this.$route.query.name
    let openid = this.$route.query.openid
    // 调用父vue组件的方法
    window.parent['loginCallback'](name, token, openid)
  }

}
</script>

<style scoped>

</style>
