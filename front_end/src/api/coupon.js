import { axios } from '@/utils/request'
const api = {
    couponPre: '/api/coupon'
};

export function hotelBirthdayCouponAPI(data) {
    return axios({
        url: `${api.couponPre}/hotelBirthdayCoupon`,
        method: 'POST',
        data,
    })
}
export function hotelTargetMoneyAPI(data) {
    return axios({
        url: `${api.couponPre}/hotelTargetMoney`,
        method: 'POST',
        data,
    })
}
export function hotelTargetRoomAPI(data) {
    return axios({
        url: `${api.couponPre}/hotelTargetRoom`,
        method: 'POST',
        data,
    })
}
export function hotelTimeCouponAPI(data) {
    return axios({
        url: `${api.couponPre}/hotelTimeCoupon`,
        method: 'POST',
        data,
    })
}
export function hotelCoopEnterpriseCouponAPI(data) {
    return axios({
        url: `${api.couponPre}/hotelCoopEnterpriseCoupon`,
        method: 'POST',
        data,
    })
}

export function hotelVIPCouponAPI(data) {
    return axios({
        url: `${api.couponPre}/hotelVipCoupon`,
        method: 'POST',
        data,
    })
}


export function hotelAllCouponsAPI(hotelId) {
    return axios({
        url: `${api.couponPre}/hotelAllCoupons`,
        method: 'GET',
        params: {hotelId: hotelId},
    })
}
export function websiteAllCouponsAPI() {
    return axios({
        url: `${api.couponPre}/websiteAllCoupons`,
        method: 'GET',

    })
}

export function orderMatchCouponsAPI(params) {
    return axios({
        url: `${api.couponPre}/orderMatchCoupons`,
        method: 'GET',
        params,
    })
}
export function deleteCouponAPI(params) {
    return axios({
        url: `${api.couponPre}/deleteCoupon`,
        method: 'POST',
        params,
    })
}