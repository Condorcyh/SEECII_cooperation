import { userCommentAPI } from '@/api/hotelManager'

import {
    getAllOrdersAPI,
    changeOrderStateAPI,
    deleteOrderAPI
} from '@/api/order'

import {
    hotelAllCouponsAPI,
    hotelTargetMoneyAPI,
    hotelTimeCouponAPI,
    hotelVIPCouponAPI,
    deleteCouponAPI
} from '@/api/coupon'

import {
    getVIPRulesAPI,
    modifyVIPRulesAPI
} from '@/api/hotel'

import { message } from 'ant-design-vue'

const marketer = {
    state: {
        allOrders: [],
        addHotelParams: {
            name: '',
            address: '',
            bizRegion:'',
            hotelStar:'',
            rate: 0,
            description:'',
            phoneNum:'',
            managerId:'',
            businessDistrictDiscount:'',
            VIPLevel:'',
            VIPLevelDiscount:'',
        },
        addHotelModalVisible: false,
        addRoomParams: {
            roomType: '',
            hotelId: '',
            price: '',
            total: 0,
            curNum: 0,
        },
        addRoomModalVisible: false,
        couponVisible: false,
        addCouponVisible: false,
        websiteHotelId: 0,
        websiteCoupons:[],
        //vip信用值规则
        vipRules:{
            vip1:100,
            vip2:300,
            vip3:600,
            vip4:1200
        }
    },
    mutations: {
        set_allOrders: function(state, data) {
            state.allOrders = data
        },
        set_addHotelModalVisible: function(state, data) {
            state.addHotelModalVisible = data
        },
        set_addHotelParams: function(state, data) {
            console.log(data);
            state.addHotelParams = {
                ...state.addHotelParams,
                ...data,
            }
        },
        set_addRoomModalVisible: function(state, data) {
            state.addRoomModalVisible = data
        },
        set_addRoomParams: function(state, data) {
            state.addRoomParams = {
                ...state.addRoomParams,
                ...data,
            }
        },
        set_couponVisible: function(state, data) {
            state.couponVisible = data
        },
        set_websiteHotelId: function(state, data) {
            state.websiteHotelId = data
        },
        set_websiteCoupons: function(state, data) {
            state.websiteCoupons = data
        },
        set_addCouponVisible: function(state, data) {
            state.addCouponVisible =data
        },
        set_vipRules:function(state, data) {
            state.vipRules =data
        },
    },
    actions: {
        //获取网站所有订单
        getAllOrders: async({ state, commit }) => {
            const res = await getAllOrdersAPI();
            if(res){
                commit('set_allOrders', res)
            }
        },
        //更改订单状态
        changeOrderState_:async({ state, dispatch,commit },data) => {
            const res = await changeOrderStateAPI(data);
            if(res){
                dispatch('getAllOrders')
            }
        },
        //删除订单
        deleteOrder_: async({ state, dispatch, commit },params) => {
            await deleteOrderAPI(params);
            dispatch('getAllOrders');
            message.success('删除成功')
        },

        //获取网站优惠策略
        getWebsiteCoupons: async({ state, commit }) => {
            const res = await hotelAllCouponsAPI(0);
            if(res) {
                // 获取到酒店策略之后的操作（将获取到的数组赋值给websiteCoupons）
                commit('set_websiteCoupons', res)
            }
        },
        //删除网站优惠策略
        deleteWebsiteCoupon: async({ commit, dispatch }, data) => {
            const res = await deleteCouponAPI(data);
            if(res){
                // 添加成功后的操作（提示文案、modal框显示与关闭，调用优惠列表策略等）
                dispatch('getWebsiteCoupons');
                message.success('删除成功')
            }else{
                // 添加失败后的操作
                message.error('删除失败')
            }
        },
        //添加网站满减优惠
        addWebsiteTargetMoneyCoupon: async({ commit, dispatch }, data) => {
            const res = await hotelTargetMoneyAPI(data);
            if(res){
                dispatch('getWebsiteCoupons');
                commit('set_addCouponVisible', false);
                message.success('添加策略成功')
            }else{
                // 添加失败后的操作
                message.error('添加失败')
            }
        },
        //添加网站限时优惠
        addWebsiteTimeCoupon: async({ commit, dispatch }, data) => {
            const res = await hotelTimeCouponAPI(data);
            if(res){
                // 添加成功后的操作（提示文案、modal框显示与关闭，调用优惠列表策略等）
                dispatch('getWebsiteCoupons');
                commit('set_addCouponVisible', false);
                message.success('添加策略成功')
            }else{
                // 添加失败后的操作
                message.error('添加失败')
            }
        },
        //添加网站VIP优惠
        addWebsiteVIPCoupon: async({ commit, dispatch }, data) => {
            const res = await hotelVIPCouponAPI(data);
            if(res){
                // 添加成功后的操作（提示文案、modal框显示与关闭，调用优惠列表策略等）
                dispatch('getWebsiteCoupons');
                commit('set_addCouponVisible', false);
                message.success('添加策略成功')
            }else{
                // 添加失败后的操作
                message.error('添加失败')
            }
        },
        //获取VIP信用值规则
        getVIPRules: async({ state, commit }) => {
            const res = await getVIPRulesAPI();
            if(res) {
                // 获取到酒店策略之后的操作（将获取到的数组赋值给websiteCoupons）
                commit('set_vipRules', res)
            }
        },
        //修改VIP信用值规则
        modifyVIPRules:async({ state, dispatch,commit },data) => {
            const res = await modifyVIPRulesAPI(data);
            if(res){
                dispatch('getVIPRules')
            }
        },
        userCommentGet: async({ state, commit }, data) => {
            const res = await userCommentAPI(data);
            console.log(res);
            if(res){
                message.success('评论成功')
            }
        },
    }
};
export default marketer