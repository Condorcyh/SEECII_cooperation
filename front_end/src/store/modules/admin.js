import {
    getManagerListAPI,
    addManagerAPI,
    addHotelAPI,
    deleteHotelAPI,
    deleteUserAPI
} from '@/api/admin'
import {
    updateUserInfoAPI
} from '@/api/user'

import { message } from 'ant-design-vue'

const admin = {
    state: {
        managerList: [],
        addManagerModalVisible: false,
        addManagerParams: {
            userType:'',
            email:'',
            password:''
        },
        addHotelParams: {
            name: '',
            address: '',
            bizRegion:'',
            hotelStar:'',
            rate: 0,
            description:'',
            phoneNum:'',
            managerId:'',
            VIPLevel:'',
            VIPLevelDiscount:'',
        },
        modifyUserInfoVisible:false
    },
    mutations: {
        set_managerList: function(state, data) {
            state.managerList = data
        },
        set_addManagerModalVisible: function(state, data) {
            state.addManagerModalVisible = data
        },
        set_addManagerParams: function(state, data) {
            state.addManagerParams = {
                ...state.addManagerParams,
                ...data,
            }
        },
        set_addHotelParams: function(state, data) {
            console.log(data);
            state.addHotelParams = {
                ...state.addHotelParams,
                ...data,
            }
        },
        set_modifyUserInfoVisible:(state,data) => {
            state.modifyUserInfoVisible = data
        }
    },
    actions: {
        //获取所有用户列表
        getManagerList: async({ commit }) => {
            const res = await getManagerListAPI();
            if(res){
                commit('set_managerList', res)
            }
        },
        //修改用户信息
        updateManagerInfo: async({ state, dispatch }, data) => {

            const res = await updateUserInfoAPI(data);
            if(res){
                message.success('修改成功');
                dispatch('getManagerList')
            }
        },
        //删除用户
        deleteUser: async({ state, dispatch, commit },data) => {
            await deleteUserAPI(data);
            dispatch('getManagerList');
            message.success('删除成功')

        },
        //添加酒店工作人员或营销人员
        addManager: async({ state, commit, dispatch }) => {
            const res = await addManagerAPI(state.addManagerParams);
            if(res) {
                commit('set_addManagerParams',{
                    userType: '',
                    email:'',
                    password:''
                });
                commit('set_addManagerModalVisible', false);
                message.success('添加成功');
                dispatch('getManagerList')
            }else{
                message.error('添加失败')
            }
        },
        //添加酒店
        addHotel: async({ state, dispatch, commit }) => {
            const res = await addHotelAPI(state.addHotelParams);
            console.log(res);
            if(res){
                dispatch('getHotelList');
                commit('set_addHotelParams', {
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
                });
                commit('set_addHotelModalVisible', false);
                message.success('添加成功')
            }else{
                message.error('添加失败')
            }
        },
        //删除酒店
        deleteHotel: async({ state, dispatch, commit },data) => {
            await deleteHotelAPI(data);
            dispatch('getHotelList');
            message.success('删除成功')

        },
    }
};
export default admin