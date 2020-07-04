import { addRoomAPI } from '@/api/hotelManager'

import {
    getHotelOrdersAPI,
    changeOrderStateAPI,
    deleteOrderAPI,
    orderStateJudgeAPI
} from '@/api/order'

import {
    getHotelByIdAPI,
    updateHotelInfoAPI,
    modifyRoomInfoAPI,
}from '@/api/hotel'

import {
    hotelAllCouponsAPI,
    hotelBirthdayCouponAPI,
    hotelTargetMoneyAPI,
    hotelTargetRoomAPI,
    hotelTimeCouponAPI,
    hotelCoopEnterpriseCouponAPI,
    hotelVIPCouponAPI,
    deleteCouponAPI,
} from '@/api/coupon'

import { message } from 'ant-design-vue'

const hotelManager = {
    state: {
        orderList: [],
        addHotelModalVisible: false,
        addRoomParams: {
            roomType: '',
            hotelId: '',
            price: '',
            total: 0,
            curNum: 0,
            bedType:'',
            peopleNum:0,
            breakfast:0,
            wifi:0
        },
        hotelInfo:{},
        addRoomModalVisible: false,
        modifyRoomInfoVisible:false,
        couponVisible: false,
        addCouponVisible: false,
        activeHotelId: 1,
        //酒店工作人员对应的酒店优惠策略
        couponList: [],
    },
    mutations: {
        set_hotelInfo: function(state, data) {
            state.hotelInfo = data
        },
        set_orderList: function(state, data) {
            state.orderList = data
        },
        set_addHotelModalVisible: function(state, data) {
            state.addHotelModalVisible = data
        },

        set_modifyRoomInfoVisible: function(state, data) {
            state.modifyRoomInfoVisible = data
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
        set_activeHotelId: function(state, data) {
            state.activeHotelId = data
        },
        set_couponList: function(state, data) {
            state.couponList = data
        },
        set_addCouponVisible: function(state, data) {
            state.addCouponVisible =data
        }
    },
    actions: {
        //获取该酒店所有的酒店订单
        getHotelOrders: async({ state, commit }) => {
            const res = await getHotelOrdersAPI(state.activeHotelId);
            if(res){
                commit('set_orderList', res)
            }
        },
        //获取酒店详细信息
        getHotelInfo: async({commit, state}) => {
            const res = await getHotelByIdAPI({
                hotelId: state.activeHotelId
            });
            if(res){
                commit('set_hotelInfo', res)
            }
        },
        //更新酒店详细信息
        updateHotelInfo:async({commit,dispatch, state},data) => {
            const res = await updateHotelInfoAPI(data);
            if(res){
                dispatch('getHotelInfo')
            }
        },
        //改变订单状态
        changeOrderState:async({ state, dispatch,commit },data) => {
            const res = await changeOrderStateAPI(data);
            if(res){
                dispatch('getHotelOrders')
            }
        },
        //删除酒店订单
        deleteOrder: async({ state, dispatch, commit },params) => {
            await deleteOrderAPI(params);
            dispatch('getHotelOrders');
            message.success('删除成功')
        },
        //录入房间
        addRoom: async({ state, dispatch, commit }) => {
            const res = await addRoomAPI(state.addRoomParams);
            if(res){
                dispatch('getHotelInfo');
                commit('set_addRoomParams', {
                    roomType: '',
                    hotelId: '',
                    price: '',
                    total: 0,
                    curNum: 0,
                });
                message.success('添加成功')
            }else{
                message.error('添加失败')
            }
        },
        //修改房间信息
        modifyRoomInfo: async({ state, dispatch, commit },data) => {
            const res = await modifyRoomInfoAPI(data);
            if(res){
                dispatch('getHotelInfo');
                message.success('修改成功')
            }else{
                message.error('修改失败')
            }
        },
        //获得酒店优惠策略
        getHotelCoupon: async({ state, commit }) => {
            const res = await hotelAllCouponsAPI(state.activeHotelId);
            if(res) {
                // 获取到酒店策略之后的操作（将获取到的数组赋值给couponList）
                commit('set_couponList', res)
            }
        },
        //删除酒店优惠策略
        deleteHotelCoupon: async({ commit, dispatch }, data) => {
            const res = await deleteCouponAPI(data);
            if(res){
                // 添加成功后的操作（提示文案、modal框显示与关闭，调用优惠列表策略等）
                dispatch('getHotelCoupon');
                message.success('删除成功')
            }else{
                // 添加失败后的操作
                message.error('删除失败')
            }

        },
        //添加生日优惠策略
        addHotelBirthdayCoupon: async({ commit, dispatch }, data) => {
            const res = await hotelBirthdayCouponAPI(data);
            if(res){
                // 添加成功后的操作（提示文案、modal框显示与关闭，调用优惠列表策略等）
                dispatch('getHotelCoupon');
                commit('set_addCouponVisible', false);
                commit('set_couponVisible',true);
                message.success('添加策略成功')
            }else{
                // 添加失败后的操作
                message.error('添加失败')
            }
        },
        //添加满减优惠策略
        addHotelTargetMoneyCoupon: async({ commit, dispatch }, data) => {
            const res = await hotelTargetMoneyAPI(data);
            if(res){
                // 添加成功后的操作（提示文案、modal框显示与关闭，调用优惠列表策略等）
                dispatch('getHotelCoupon');
                commit('set_addCouponVisible', false);
                commit('set_couponVisible',true);
                message.success('添加策略成功')
            }else{
                // 添加失败后的操作
                message.error('添加失败')
            }

        },
        //添加多间优惠策略
        addHotelTargetRoomCoupon: async({ commit, dispatch }, data) => {
            const res = await hotelTargetRoomAPI(data);
            if(res){
                // 添加成功后的操作（提示文案、modal框显示与关闭，调用优惠列表策略等）
                dispatch('getHotelCoupon');
                commit('set_addCouponVisible', false);
                commit('set_couponVisible',true);
                message.success('添加策略成功')
            }else{
                // 添加失败后的操作
                message.error('添加失败')
            }
        },
        //添加限时优惠策略
        addHotelTimeCoupon: async({ commit, dispatch }, data) => {
            const res = await hotelTimeCouponAPI(data);
            if(res){
                // 添加成功后的操作（提示文案、modal框显示与关闭，调用优惠列表策略等）
                dispatch('getHotelCoupon');
                commit('set_addCouponVisible', false);
                commit('set_couponVisible',true);
                message.success('添加策略成功')
            }else{
                // 添加失败后的操作
                message.error('添加失败')
            }
        },
        //添加合作企业优惠策略
        addHotelCoopEnterpriseCoupon: async({ commit, dispatch }, data) => {
            const res = await hotelCoopEnterpriseCouponAPI(data);
            if(res){
                // 添加成功后的操作（提示文案、modal框显示与关闭，调用优惠列表策略等）
                dispatch('getHotelCoupon');
                commit('set_addCouponVisible', false);
                commit('set_couponVisible',true);
                message.success('添加策略成功')
            }else{
                // 添加失败后的操作
                message.error('添加失败')
            }
        },
        //添加VIP优惠策略
        addHotelVIPCoupon: async({ commit, dispatch }, data) => {
            const res = await hotelVIPCouponAPI(data);
            if(res){
                // 添加成功后的操作（提示文案、modal框显示与关闭，调用优惠列表策略等）
                dispatch('getHotelCoupon');
                commit('set_addCouponVisible', false);
                commit('set_couponVisible',true);
                message.success('添加策略成功')
            }else{
                // 添加失败后的操作
                message.error('添加失败')
            }
        },

        //更新订单状态
        judgeOrder:async ({state,commit},date)=>{
            const res = await orderStateJudgeAPI();
            if(res){
                message.success('修改成功，请刷新界面查看')
            }
        }
    }
};
export default hotelManager