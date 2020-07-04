import { axios } from '@/utils/request'
const api = {
    orderPre: '/api/order'
};

export function reserveHotelAPI(data) {
    return axios({
        url: `${api.orderPre}/addOrder`,
        method: 'POST',
        data,
    })
}
export function getAllOrdersAPI() {
    return axios({
        url: `${api.orderPre}/getAllOrders`,
        method: 'GET',
    })
}
export function getHotelOrdersAPI(hotelId) {
    return axios({
        url: `${api.orderPre}/${hotelId}/allOrders`,
        method: 'GET',
    })
}
export function changeOrderStateAPI(params) {
    return axios({
        url: `${api.orderPre}/changeOrderState`,
        method: 'POST',
        params:params,
    })
}
export function deleteOrderAPI(params) {
    return axios({
        url: `${api.orderPre}/deleteOrder`,
        method: 'POST',
        params:params
    })
}
export function getUserOrdersAPI(data) {
    return axios({
        url: `${api.orderPre}/${data.userId}/getUserOrders`,
        method: 'GET',
    })
}
export function cancelOrderAPI(orderId) {
    return axios({
        url: `${api.orderPre}/${orderId}/annulOrder`,
        method: 'GET',
    })
}
export function getMostFavoredAccountAPI(orderId) {
    return axios({
        url: `${api.orderPre}/${orderId}/mostFavoredaccount`,
        method: 'GET',
    })
}

export function orderStateJudgeAPI() {
    return axios({
        url: `${api.orderPre}/orderStateJudge`,
        method: 'GET',
    })
}