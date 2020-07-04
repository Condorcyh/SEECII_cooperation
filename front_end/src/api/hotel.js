import { axios } from '@/utils/request'
const api = {
    hotelPre: '/api/order'
};

export function getHotelsAPI() {
    return axios({
        url: `${api.hotelPre}/all`,
        method: 'get',

    })
}
export function filterHotelsAPI(data) {
    return axios({
        url: `api/hotel/all`,
        method: 'Post',
        data
    })
}
export function addressFilterAPI(params) {
    return axios({
        url: `api/hotel/address`,
        method: 'Get',
       params
    })
}
export function priceSortAPI(param) {
    return axios({
        url: `api/hotel/priceSort`,
        method: 'Get',
        params:param
    })
}
export function rateSortAPI() {
    return axios({
        url: `api/hotel/rateSort`,
        method: 'Get',
    })
}

export function getHotelByIdAPI(param) {
    return axios({
        url: `${api.hotelPre}/${param.hotelId}/detail`,
        method: 'GET',
    })
}

export function updateHotelInfoAPI(data) {
    return axios({
        url: `api/hotel/updateHotelInfo`,
        method: 'POST',
        data
    })
}
export function modifyRoomInfoAPI(data) {
    return axios({
        url: `api/hotel/modifyRoomInfo`,
        method: 'POST',
        data
    })
}
export function getVIPRulesAPI() {
    return axios({
        url: `api/hotel/getVIPRules`,
        method: 'GET',
    })
}
export function modifyVIPRulesAPI(data) {
    return axios({
        url: `/api/hotel/modifyVIPRules`,
        method: 'POST',
        data
    })
}
export function getCommentsByIdAPI(param) {
    return axios({
        url: `/api/hotel/${param.hotelId}/comments`,
        method: 'GET',

    })
}
export function addCommentAPI(data) {
    return axios({
        url: `/api/hotel/addComment`,
        method: 'POST',
        data
    })
}