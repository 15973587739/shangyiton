import request from "../utils/request";

const api_name = `/api/user/`

export default {
  login(userInfo){
    return request({
      url: `${api_name}login`,
      method: 'post',
      data: userInfo
    })
  },
  saveUserAuth(userAuth){
    return request({
      url: `${api_name}auth/userAuth`,
      method: 'post',
      data: userAuth
    })
  },
  // 根据当前登录的用户获取当前登录用户的信息
  getUserInfo(){
    return request({
      url: `${api_name}auth/getUserInfo`,
      method: 'get'
    })
  }

}
