import router from '@/router'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { resetRouter } from '@/router'
import { message } from 'ant-design-vue'
import {
    loginAPI,
    registerAPI,
    getUserInfoAPI,
    updateUserInfoAPI,
    iconChooseAPI,
    getvipLevelAPI,
    updatevipLevelAPI,
    creditChargeAPI,
} from '@/api/user'

import {
    getUserOrdersAPI,
    cancelOrderAPI,
} from '@/api/order'

import { addCommentAPI }from '@/api/hotel'

const getDefaultState = () => {
    return {
        userId: '',
        userInfo: {},
        vipLevel:0,
        userOrderList: [],
        iconChoose: "",
        iconChooseVisible: false,
        creditChargeVisible: false,
    }
};

const user = {
    state : getDefaultState(),

    mutations: {
        reset_state: function(state) {
            state.token = '';
            state.userInfo = {};
            state.userId = '';
            state.userOrderList = [];
            state.iconChoose = "";
            state.iconChooseVisible = false;
            state.creditChargeVisible = false
        },
        set_vipLevel:function(state, data){
            state.vipLevel = data
        },
        set_token: function(state, token){
            state.token = token
        },
        set_email: (state, data) => {
            state.email = data
        },
        set_userId: (state, data) => {
            state.userId = data
        },
        set_userInfo: (state, data) => {
            state.userInfo = {
                ...state.userInfo,
                ...data
            }
        },
        set_userOrderList: (state, data) => {
            state.userOrderList = data
        },
        set_iconChoose: (state,data)=>{
            state.iconChoose = data
        },
        set_iconChooseVisible:(state,data) => {
            state.iconChooseVisible = data
        },
        set_creditChargeVisible:(state,data) => {
            state.creditChargeVisible = data
        },

    },

    actions: {
        login: async ({ dispatch, commit }, userData) => {
            const res = await loginAPI(userData);
            if(res){
                setToken(res.id);
                commit('set_userId', res.id);
                commit('set_iconChoose',res.avatar);
                dispatch('getUserInfo');
                router.push('/hotel/hotelList')
            }
        },
        register: async({ commit }, data) => {
            const res = await registerAPI(data);
            if(res){
                message.success('注册成功')
            }
        },
        getUserInfo({ state, commit }) {
            return new Promise((resolve, reject) => {
              getUserInfoAPI(state.userId).then(response => {
                const data = response;
                if (!data) {
                  reject('登录已过期，请重新登录')
                }
                commit('set_userInfo', data);
                commit('set_userId', data.id);
                resolve(data)
              }).catch(error => {
                reject(error)
              })
            })
        },
        //获取酒店vip等级
        getvipLevel:async ({ state, commit })=>{
            const res = await getvipLevelAPI(state.userId);
                commit("set_vipLevel",res)
        },
        //更新酒店vip等级
        updatevipLevel:async ({ state, dispatch,commit })=>{
            const res = await updatevipLevelAPI(state.userId);
            if(typeof res!="undefined"){
                dispatch("getvipLevel")
            }
        },
        //更新用户信息
        updateUserInfo: async({ state, dispatch }, data) => {
            const params = {
                id: state.userId,
                ...data,
            };
            const res = await updateUserInfoAPI(params);
            if(res){
                message.success('修改成功');
                dispatch('getUserInfo')
            }
        },

        //获取用户订单
        getUserOrders: async({ state, commit }) => {
            const data = {
                userId: Number(state.userId)
            };
            const res = await getUserOrdersAPI(data);
            if(res){
                commit('set_userOrderList', res);
                console.log("userOrderList",state.userOrderList)
            }
        },
        //取消订单
        cancelOrder: async({ state, dispatch }, orderId) => {
            const res = await cancelOrderAPI(orderId);
            if(res) {
                dispatch('getUserOrders');
                message.success('撤销成功')
            }else{
                message.error('撤销失败')
            }
        },
        logout: async({ commit }) => {
            removeToken();
            resetRouter();
            commit('reset_state')
        },
          // remove token
        resetToken({ commit }) {
            return new Promise(resolve => {
                removeToken(); // must remove  token  first
                commit('reset_state');
                resolve()
            })
        },

        //icon配置相关函数
        iconChoice: async({ state, dispatch,commit}, data) => {
            const res = await iconChooseAPI(data);
            if(res){
                message.success('设置成功');
                commit('set_iconChooseVisible', false);
                dispatch('getUserInfo')
            }
            else{
                message.error("设置失败")
            }
        },

        userCreditCharge: async({ state, commit }, data) => {
            console.log(data);
            const res = await creditChargeAPI(data);
            console.log(res);
            if(res){
                message.success('充值成功');
                commit('set_creditChargeVisible', false)
            }
        },
        addComment: async({ state, commit }, data) => {

            const res = await addCommentAPI(data);
            if(res){
                message.success('评论成功')
            }else{
                message.error("评论失败")
            }
        },
    }
};

export default user