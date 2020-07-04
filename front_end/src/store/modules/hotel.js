import { message } from 'ant-design-vue'

import {
    getHotelsAPI,
    getHotelByIdAPI,
    getCommentsByIdAPI,
    filterHotelsAPI,
    addressFilterAPI,
    priceSortAPI,
    rateSortAPI,
} from '@/api/hotel'

import {
    reserveHotelAPI
} from '@/api/order'

import {
    orderMatchCouponsAPI,
    websiteAllCouponsAPI
} from '@/api/coupon'

const hotel = {
    state: {
        hotelList: [],
        rankAll:true,
        typeAll:true,
        hotelListParams: {
            pageNo: 0,
            pageSize: 8
        },
        hotelListLoading: true,
        currentHotelId: '',
        currentHotelInfo: {},
        orderModalVisible: false,
        currentOrderRoom: {},

        //@auther: 刁苏阳
        // 仿写currentHotelInfo撰写userComment
        // 在state中加入状态，在mutations中加入set_userComment方法，在actions中加入getCommentsById方法
        userComment:[],
        addRegion:{
            address:'南京',
            bizRegion:'XiDan'
        },
        bizRegion:'XiDan',
        orderMatchCouponList: [],
        isShowHistoryOrders:false,
        websiteAllCouponList:[],
        hotelOrderList:[],
    },
    mutations: {
        set_hotelList: function(state, data) {
            state.hotelList = data
        },
        set_addRegion: function(state, data) {
            state.addRegion = data
        },
        set_rankAll: function(state, data) {
            state.rankAll = data
        },
        set_typeAll: function(state, data) {
            state.typeAll= data
        },
        set_hotelListParams: function(state, data) {
            state.hotelListParams = {
                ...state.hotelListParams,
                ...data,
            }
        },
        set_hotelListLoading: function(state, data) {
            state.hotelListLoading = data
        },
        set_currentHotelId: function(state, data) {
            state.currentHotelId = data
        },
        set_currentHotelInfo: function(state, data) {
            state.currentHotelInfo = {
                ...state.currentHotelInfo,
                ...data,
            }
        },
        set_userComment: function(state,data){
            state.userComment = data
        },
        set_orderModalVisible: function(state, data) {
            state.orderModalVisible = data
        },
        set_currentOrderRoom: function(state, data) {
            state.currentOrderRoom = {
                ...state.currentOrderRoom,
                ...data,
            }
        },
        set_orderMatchCouponList: function(state, data) {
            state.orderMatchCouponList = data
        },
        set_websiteAllCouponList: function(state, data) {
            state.websiteAllCouponList = data
        },
        set_isShowHistoryOrders: function(state, data) {
            state.isShowHistoryOrders = data
        },
        set_hotelOrderList: function(state, data) {
            state.hotelOrderList=data
        },
    },

    actions: {
        //获取酒店列表
        getHotelList: async({commit, state}) => {
            const res = await getHotelsAPI();
            if(res){
                console.log("res",res);
                commit('set_hotelList', res);
                commit('set_hotelListLoading', false)
            }
        },
        //筛选酒店
        filterHotelList:async({commit, state,dispatch},data) => {
            const res = await addressFilterAPI(state.addRegion);
            if(res){
                dispatch('filterHotelList2',data);
                commit('set_hotelListLoading', false)
            }

        },
        filterHotelList2:async({commit, state},data) => {
            const res = await filterHotelsAPI(data);
            if(res){
                commit('set_hotelList',res)
            }
        },
        //价格排序
        priceSort:async({commit, state},data) => {
            const res = await priceSortAPI(data);
            if(res){
                commit('set_hotelList', res);
                commit('set_hotelListLoading', false)
            }
        },
        //评分排序
        rateSort:async({commit, state},data) => {
            const res = await rateSortAPI();
            if(res){
                commit('set_hotelList', res);
                commit('set_hotelListLoading', false)
            }
        },
        //地址商圈筛选
        addRegionFilter:async({commit, state}) => {
            console.log("data",state.addRegion);
            const res = await addressFilterAPI(state.addRegion);
            if(res){
                console.log("res",res);
                commit('set_hotelList', res);
                commit('set_hotelListLoading', false)
            }
        },
        //获取某个酒店的详细信息
        getHotelById: async({commit, state}) => {
            const res = await getHotelByIdAPI({
                hotelId: state.currentHotelId
            });
            if(res){
                commit('set_currentHotelInfo', res)
            }
        },
        //添加订单
        addOrder: async({ state, dispatch,commit }, data) => {
            const res = await reserveHotelAPI(data);
            console.log(res);
            if(res){
                message.success('预定成功');
                dispatch('getHotelById')
            }
        },
        //预订酒店时获得匹配的优惠策略
        getOrderMatchCoupons: async({ state, commit }, data) => {
            const res = await orderMatchCouponsAPI(data);
            if(res){
                if(data.hotelId!==0){
                    commit('set_orderMatchCouponList', res)
                }
                else
                    commit('set_websiteAllCouponList',res)
            }
        },
        //获取网站优惠策略
        getWebsiteAllCoupons: async({ state, commit }) => {
            const res = await websiteAllCouponsAPI();
            if(res){
                commit('set_websiteAllCouponList', res)
            }
        },
        //根据酒店ID获取所有评论
        getCommentsById: async({commit, state}) => {
            console.log("comment");
            const res = await getCommentsByIdAPI({
                hotelId: state.currentHotelId
            });

            if(res){
                commit('set_userComment', res)
            }
        },
    }
};

export default hotel