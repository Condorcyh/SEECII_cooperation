import { axios } from '@/utils/request'
const api = {
    adminPre: '/api/admin'
};
export function getManagerListAPI(){
    return axios({
        url: `${api.adminPre}/getAllManagers`,
        method: 'POST'
    })
}
export function addManagerAPI(data) {
    return axios({
        url: `${api.adminPre}/addManager`,
        method: 'POST',
        data
    })
}
export function deleteUserAPI(param) {
    return axios({
        url: `${api.adminPre}/deleteUser`,
        method: 'POST',
        params:param
    })
}
export function addHotelAPI(data) {
    return axios({
        url: `/api/hotel/addHotel`,
        method: 'POST',
        data,
    })
}
export function deleteHotelAPI(param) {
    return axios({
        url: `/api/hotel/deleteHotel`,
        method: 'POST',
        params:param,
    })
}