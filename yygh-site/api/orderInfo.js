import request from "../utils/request";

const api_name = '/api/order/orderInfo'

export default {

  saveOrder(scheduleId, patientId){
    return request({
      url: `${api_name}/auth/submitOrder/${scheduleId}/${patientId}`,
      method: 'post'
    })
  },

  getOrder(orderId){
    return request({
      url: `${api_name}/auth/getOrder/${orderId}`,
      method: 'get'
    })
  },

  getPageList(page, limit, searchObj) {
    return request({
      url: `${api_name}/auth/${page}/${limit}`,
      method: 'get',
      params: searchObj
    });
  },

  getStatusList(){
    return request({
      url: `${api_name}/auth/getStatusList`,
      method: 'get'
    })
  },

  cancelOrder(orderId){
    return request({
      url: `${api_name}/auth/cancelOrder/${orderId}`,
      method: 'get'
    })
  }



}
