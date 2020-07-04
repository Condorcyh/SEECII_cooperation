import { axios } from '@/utils/request'
const api = {
    hotelPre: '/api/hotel'
};

export function addRoomAPI(data) {
    return axios({
        url: `${api.hotelPre}/roomInfo`,
        method: 'POST',
        data,
    })
}

export function userCommentAPI(data) {
    return axios({
        url: `${api.hotelPre}/userComment`,
        method: 'POST',
        data,
    })
}