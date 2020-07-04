<template>
    <a-modal
        :visible="orderModalVisible"
        title="预定详情"
        cancelText="取消"
        okText="下单"
        @cancel="cancelOrder"
        @ok="handleSubmit"
    >
        <a-form :form="form">
            <a-form-item v-bind="formItemLayout" label="房型信息">
                <span>{{ currentOrderRoom.roomType }}</span>
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="入住人姓名">
                <a-input
                    v-decorator="[
                        'clientName',
                        { rules: [{required: true, message: '请填写入住人姓名', }] }
                    ]"
                />
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="手机号">
                <a-input
                    v-decorator="[
                        'phoneNumber',
                        { rules: [{required: true, message: '请填写入住人联系手机', }] }
                    ]"
                />
            </a-form-item>

            <a-form-item v-bind="formItemLayout" label="入住日期">
                <a-range-picker
                    format="YYYY-MM-DD"
                    @change="changeDate"
                    :disabled-date="disabledDate"
                    v-decorator="[
                        'date',
                        {
                            rules: [{ required: true, message: '请选择入住时间' },{ validator: this.judgeCheckInTime }]
                        }
                    ]"
                    :placeholder="['入住日期','退房日期']"
                />
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="入住人数">
                <a-select
                    v-decorator="[
                        'peopleNum',
                        { rules: [{ required: true, message: '请选择入住人数' }] },
                    ]"
                    placeholder="请选择入住人数"
                    @change="changePeopleNum"
                >
                    <a-select-option value="1">1</a-select-option>
                    <a-select-option value="2">2</a-select-option>
                     <a-select-option value="3">3</a-select-option>
                    <a-select-option value="4">4</a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="有无儿童">
                <a-radio-group
                    v-decorator="[
                        'haveChild',
                        { rules: [{required: true, message: '请选择有无儿童入住', }] }
                    ]"
                >
                    <a-radio :value="1">有</a-radio>
                    <a-radio :value="0">无</a-radio>
                </a-radio-group>
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="房间数">
                <a-select
                    v-decorator="[
                        'roomNum',
                        { rules: [{ required: true, message: '请选择房间数' }] },
                    ]"
                    placeholder="请选择房间数"
                    @change="changeRoomNum"
                >
                    <a-select-option v-for="count in currentOrderRoom.curNum" :value="count" :key="count">{{count}}</a-select-option>

                </a-select>
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="房间单价">
                <span>{{ currentOrderRoom.price }}</span>
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="总价">
                <span>￥{{ totalPrice }}</span>
            </a-form-item>
            <a-divider></a-divider>
            <a-form-item v-bind="formItemLayout" label="优惠">
                <a-radio-group v-model="checkedItem" @change="onchange">
                    <a-table
                            :columns="columns"
                            :dataSource="orderMatchCouponList"
                            :pagination="false"
                            bordered
                            size="small"
                            v-if="orderMatchCouponList.length>0"

                    >
                        <a-radio
                                slot="id"
                                slot-scope="record"
                                :value="record"
                        >
                        </a-radio>
                        <span slot="couponType" slot-scope="text">
                        <span>{{coupontype[text]}}</span>
                    </span>
                    </a-table>
                </a-radio-group>
                <a-radio-group v-model="checkedItem_2" @change="onchange_2">
                    <a-table
                            :columns="columns"
                            :dataSource="websiteAllCouponList"
                            :pagination="false"
                            bordered
                            size="small"
                            v-if="websiteAllCouponList.length>0"

                    >
                        <a-radio
                                slot="id"
                                slot-scope="record"
                                :value="record"
                        >
                        </a-radio>
                        <span slot="couponType" slot-scope="text">
                            <span>{{coupontype[text]}}</span>
                        </span>
                    </a-table>
                </a-radio-group>
            </a-form-item>
             <a-form-item v-bind="formItemLayout" label="结算后总价">
                <span>￥{{ finalPrice }}</span>
            </a-form-item>
        </a-form>
    </a-modal>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
import {orderMatchCouponsAPI} from "../../../api/coupon";
const moment = require('moment');
const columns = [
    {
        title: '',
        dataIndex: 'id',
        scopedSlots: {customRender: 'id'}
    },
    {
        title: '优惠券名称',
        dataIndex: 'couponName',
    },

    {
        title: '优惠类型',
        dataIndex: 'couponType',
        scopedSlots: {customRender: 'couponType'}
    },

  ];
export default {
    name: 'orderModal',
    data() {
        return {
            formItemLayout: {
                labelCol: {
                    xs: { span: 12 },
                    sm: { span: 6 },
                },
                wrapperCol: {
                    xs: { span: 24 },
                    sm: { span: 16 },
                },
            },
            totalPrice: 0,
            columns,
            checkedItem: null,
            checkedItem_2:null,
            finalPrice: 0,
            coupontype:['','生日特惠' ,'多间特惠' ,'满减优惠' ,'限时优惠' ,'合作企业优惠' ,'会员优惠'],
        }
    },
    computed: {
        ...mapGetters([
            'orderModalVisible',
            'currentOrderRoom',
            'currentHotelId',
            'currentHotelInfo',
            'userId',
            'orderMatchCouponList',
            'websiteAllCouponList'
        ]),

    },
    beforeCreate() {
        this.form = this.$form.createForm(this, { name: 'orderModal' });
    },
    methods: {
        ...mapMutations([
            'set_orderModalVisible',
            'set_orderMatchCouponList',
            'set_websiteAllCouponList'
        ]),
        ...mapActions([
            'addOrder',
            'getOrderMatchCoupons',
            'getWebsiteAllCoupons'
        ]),
        disabledDate(current) {
            // Can not select days before today and today
            return current&&current < moment().startOf('day');
        },
        //取消订单时信息重置
        cancelOrder() {
            this.set_orderModalVisible(false);
            this.form.resetFields();
            this.totalPrice = 0;
            this.finalPrice = 0;
            this.set_orderMatchCouponList([]);
            this.checkedItem = null;
            this.checkedItem_2 = null;
            this.set_websiteAllCouponList([]);
        },
        //选择日期时调用
        changeDate(v) {
            if(this.form.getFieldValue('roomNum')&&this.form.getFieldValue('peopleNum')){
                this.totalPrice = this.form.getFieldValue('roomNum') * moment(v[1]).diff(moment(v[0]), 'day') * Number(this.currentOrderRoom.price)*Number(this.form.getFieldValue('peopleNum'))
            }
            this.finalPrice = this.totalPrice
        },
        //选择入住人数时调用
        changePeopleNum(v){
            if(this.form.getFieldValue('roomNum')&&this.form.getFieldValue('date')){
                this.totalPrice = this.form.getFieldValue('roomNum') * moment(this.form.getFieldValue('date')[1]).diff(moment(this.form.getFieldValue('date')[0]), 'day') * Number(this.currentOrderRoom.price)*Number(v)
            }
            this.finalPrice = this.totalPrice
        },
        //选择房间数量时调用
        changeRoomNum(v) {
            if(this.form.getFieldValue('peopleNum')&&this.form.getFieldValue('date')){
                this.totalPrice = Number(v) * moment(this.form.getFieldValue('date')[1]).diff(moment(this.form.getFieldValue('date')[0]), 'day') * Number(this.currentOrderRoom.price)*Number(v)
            }
            this.finalPrice = this.totalPrice
            },
        //点击酒店优惠策略时调用
        onchange(v) {
            if(this.checkedItem){
                this.orderMatchCouponList.forEach(item=>{
                    if(item.id === this.checkedItem){
                        if(item.discountMoney>0 && this.totalPrice-item.discountMoney>0) {
                            this.finalPrice = this.totalPrice - item.discountMoney;
                        }
                        else if(item.discount>0&&Math.round(this.totalPrice*(1-item.discount))>0) {
                            this.finalPrice = Math.round(this.totalPrice * (1 - item.discount))
                        }
                    }
                })
            }else{
            }
        },
        //点击网站优惠策略时调用
        onchange_2(v) {
            if(this.checkedItem_2){
                this.websiteAllCouponList.forEach(item=>{
                    if(item.id === this.checkedItem_2){
                        if(item.discountMoney>0 && this.finalPrice-item.discountMoney>0) {
                            this.finalPrice = this.finalPrice - item.discountMoney;
                        }
                        else if(item.discount>0&&Math.round(this.finalPrice*(1-item.discount))>0) {
                            this.finalPrice = Math.round(this.finalPrice * (1 - item.discount))
                        }
                    }
                })
            }else{
            }
        },
        judgeCheckInTime(rule,value,callback){
            let checkIn = value[0]._d;
            console.log(checkIn);
            let date = new Date();
            console.log(date);
            var judge = false;
            if(date.getFullYear() < checkIn.getFullYear()){
                judge = true
            }else if(date.getFullYear() === checkIn.getFullYear() && date.getMonth() < checkIn.getMonth()){
                judge = true
            }else if(date.getFullYear() === checkIn.getFullYear() && date.getMonth() === checkIn.getMonth() && date.getDate() <= checkIn.getDate()){
                judge = true
            }
            console.log(judge);
            if(!judge){
                callback(new Error('不能够预定过去的房间'))
            }else{
                callback()
            }
        },
        //点击确定时调用
        handleSubmit(e) {
            e.preventDefault();
            this.form.validateFieldsAndScroll((err, values) => {
                if (!err) {
                    const data = {
                        hotelId: this.currentHotelId,
                        hotelName: this.currentHotelInfo.name,
                        userId: Number(this.userId),
                        checkInDate: moment(this.form.getFieldValue('date')[0]).format('YYYY-MM-DD'),
                        checkOutDate: moment(this.form.getFieldValue('date')[1]).format('YYYY-MM-DD'),
                        roomType: this.currentOrderRoom.roomType === '大床房' ? 'BigBed' : this.currentOrderRoom.roomType === '双床房' ? 'DoubleBed' : 'Family',
                        roomNum: this.form.getFieldValue('roomNum'),
                        peopleNum: this.form.getFieldValue('peopleNum'),
                        haveChild: this.form.getFieldValue('haveChild'),
                        createDate: '',
                        price: this.checkedItem ? this.finalPrice: this.totalPrice
                    };
                    this.addOrder(data);
                    this.set_orderModalVisible(false);
                    this.checkedItem=null;
                    this.checkedItem_2=null;
                    this.form.resetFields();
                    this.totalPrice=0;
                    this.finalPrice=0;
                    this.set_orderMatchCouponList([]);
                    this.set_websiteAllCouponList([])
                }
            });
        },
    },
    watch:{
        //totalPrice改变时获取匹配的优惠策略
        totalPrice(val) {
            if(val>0) {
                let data = {
                    userId: this.userId,
                    hotelId: this.currentHotelId,
                    orderPrice: Number(this.totalPrice),
                    roomNum: this.form.getFieldValue('roomNum') ? Number(this.form.getFieldValue('roomNum')) : 0,
                    checkIn: this.form.getFieldValue('date') ? moment(this.form.getFieldValue('date')[0]).format('YYYY-MM-DD') : '',
                    checkOut: this.form.getFieldValue('date') ? moment(this.form.getFieldValue('date')[1]).format('YYYY-MM-DD') : '',
                };

                this.getOrderMatchCoupons(data).then(()=>{
                    data.hotelId = 0;
                    this.getOrderMatchCoupons(data)
                })

            }
        }
    }
}
</script>