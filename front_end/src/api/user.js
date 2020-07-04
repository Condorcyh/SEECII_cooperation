import { axios } from '@/utils/request'
const api = {
    userPre: '/api/user'
};

export function loginAPI(data){
    return axios({
        url:`${api.userPre}/login`,
        method: 'POST',
        data
    })
}
export function registerAPI(data){
    return axios({
        url: `${api.userPre}/register`,
        method: 'POST',
        data
    })
}

export function getUserInfoAPI(id){
    return axios({
        url: `${api.userPre}/${id}/getUserInfo`,
        method: 'GET'
    })
}
export function getvipLevelAPI(id){
    return axios({
        url: `${api.userPre}/${id}/getUserLevel`,
        method: 'GET'
    })
}
export function updatevipLevelAPI(id){
    return axios({
        url: `${api.userPre}/${id}/updateUserLevel`,
        method: 'POST'
    })
}

export function updateUserInfoAPI(data) {
    return axios({
        url: `${api.userPre}/${data.id}/userInfo/update`,
        method: 'POST',
        data
    })
}

export function iconChooseAPI(data) {
    return axios({
        url: `${api.userPre}/${data.userId}/iconChoose`,
        method: 'POST',
        params:data
    })
}

export function creditChargeAPI(data) {
    return axios({
        url: `${api.userPre}/creditCharge`,
        method: 'POST',
        data
    })
}