<template>
    <div class="manageHotel-wrapper">

        <a-tabs>
            <a-tab-pane tab="优惠策略管理" key="1">
                <div style="width: 100%; text-align: right; margin:20px 0">
                    <a-button  type="primary" @click="addCoupon"><a-icon type="plus"/>添加优惠策略</a-button>
                </div>
                <a-tag  color="red">生日优惠</a-tag>
                <a-tag  color="orange">多间优惠</a-tag>
                <a-tag  color="blue">满减优惠</a-tag>
                <a-tag  color="pink">限时优惠</a-tag>
                <a-tag  color="purple">合作企业优惠</a-tag>
                <a-tag  color="green">VIP优惠</a-tag>
                <div style="margin-top: 10px;">
                <a-table :dataSource="couponList" :columns="columns1" bordered>
                    <a-tag slot="couponName" slot-scope="couponName,record" :color="colors[record.couponType]">{{couponName}}</a-tag>
                    <span slot="action" slot-scope="text,record" >
                      <a-popconfirm
                              title="确定想删除该优惠策略吗？"
                              @confirm="deleteCoupon(record)"
                              okText="确定"
                              cancelText="取消"
                      >
                            <a-button type="danger" size="small">删除</a-button>
                        </a-popconfirm>
                    </span>
                </a-table>
                </div>
            </a-tab-pane>
            <a-tab-pane tab="订单管理" key="2">
                <a-button class="orderJudge" style="float:right;" type="primary" @click="orderJudge">更新订单状态</a-button>
                <br>
                <a-table
                        style="margin-top: 30px;"
                    :columns="columns2"
                    :dataSource="orderList"
                    bordered
                >
                    <span slot="price" slot-scope="text">
                        <span>￥{{ text }}</span>
                    </span>
                    <span slot="roomType" slot-scope="text">
                        <span v-if="text === 'BigBed'">大床房</span>
                        <span v-if="text === 'DoubleBed'">双床房</span>
                        <span v-if="text === 'Family'">家庭房</span>
                    </span>
                    <span slot="action" slot-scope="record">
                        <a-button type="primary" size="small" @click="orderInfo(record.id)">订单详情</a-button>
                        <a-divider type="vertical"></a-divider>
                        <a-popconfirm
                            title="确定想删除该订单吗？"
                            @confirm="removeOrder(record)"
                            okText="确定"
                            cancelText="取消"
                        >
                            <a-button type="danger" size="small">删除订单</a-button>
                        </a-popconfirm>
                        <a-divider type="vertical"></a-divider>
                        <a-popover  title="改变状态" v-if="record.orderState ==='已预订'">
                            <div slot="content" class="stateBox">
                                <a href="#" v-for="(item,index) in orderState" @click="stateChange(record,index)" :key="index" class="state">{{item}}</a>
                            </div>
                            <a-button size="small" type="default">状态</a-button>
                        </a-popover>
                    </span>
                </a-table>
            </a-tab-pane>
            <a-tab-pane tab="房间管理" key="3">
                <div style="width: 100%; text-align: right; margin:20px 0">
                    <a-button  type="primary" @click="addRoom"><a-icon type="plus"/>录入房间</a-button>
                </div>
                <a-table
                        :columns="columns3"
                        :dataSource="hotelInfo.rooms"
                >
                    <span slot="price" slot-scope="text">
                        <span>￥{{ text }}</span>
                    </span>
                    <span slot="breakfast" slot-scope="text">
                        <span>{{text ===1?'有':'无' }}</span>
                    </span>
                    <span slot="wifi" slot-scope="text">
                        <span>{{text ===1?'有':'无' }}</span>
                    </span>
                    <span slot="action" slot-scope="text,record">
                    <a-button type="primary" size="small" @click="modifyRoom(record)">修改</a-button>
                    </span>
                </a-table>
            </a-tab-pane>
            <a-tab-pane tab="信息管理" key="4">

                <a-form :form="form" style="margin-top: 30px">
                    <a-form-item label="酒店名称" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1  }">
                        <a-input
                                placeholder="请填写酒店名称"
                                v-decorator="['hotelName', { rules: [{ required: true, message: '请输入酒店名称' }] }]"
                                v-if="modify"
                        />
                        <span v-else>{{ hotelInfo.name }}</span>
                    </a-form-item>
                    <a-form-item label="酒店ID" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                        <span>{{ hotelInfo.id}}</span>
                    </a-form-item>
                    <a-form-item label="评分" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                        <span>{{ hotelInfo.rate }}</span>
                    </a-form-item>

                    <a-form-item label="地址" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                        <a-input
                                placeholder="请填写地址"
                                v-decorator="['address', { rules: [{ required: true, message: '请输入地址' }] }]"
                                v-if="modify"
                        />
                        <span v-else>{{ hotelInfo.address}}</span>
                    </a-form-item>
                    <a-form-item label="商圈" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                        <a-select class="district_select"
                                  v-decorator="[
                                        'bizRegion', { rules: [{ required: true, message: '请选择商圈' }] }]"
                                  v-if="modify"
                                  @change="bizRegionChange">
                            <a-select-option v-for="(item) in bizRegion_select" :key="item">{{item}}</a-select-option>

                        </a-select>
                        <span v-else>{{ hotelInfo.bizRegion}}</span>
                    </a-form-item>
                    <a-form-item label="手机号码" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1  }">
                        <a-input
                                placeholder="请填写手机号码"
                                v-decorator="['phoneNum', { rules: [{ required: false, message: '请输入手机号码'}] }]"
                                v-if="modify"
                        />
                        <span v-else>{{ hotelInfo.phoneNum }}</span>
                    </a-form-item>
                    <a-form-item label="酒店简介" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1  }">
                        <a-input
                                type="textarea"
                                :rows="4"
                                v-if="modify"
                                placeholder="请填写酒店简介"
                                v-decorator="['description', { rules: [{ required: false, message: '请填写酒店简介' }] }]"
                        />
                        <span v-else>{{ hotelInfo.description}}</span>
                    </a-form-item>
                    <a-form-item :wrapper-col="{ span: 12, offset: 5 }" v-if="modify">
                        <a-button type="primary" @click="saveModify">
                            保存
                        </a-button>
                        <a-button type="default" style="margin-left: 30px" @click="cancelModify">
                            取消
                        </a-button>
                    </a-form-item>
                    <a-form-item :wrapper-col="{ span: 8, offset: 4 }" v-else>
                        <a-button type="primary" @click="modifyInfo">
                            修改信息
                        </a-button>
                    </a-form-item>
                </a-form>
            </a-tab-pane>
        </a-tabs>
        <AddRoomModal></AddRoomModal>
        <AddCoupon></AddCoupon>
        <modify-room-info :room-info="roomInfo"></modify-room-info>
    </div>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
import AddRoomModal from './components/addRoomModal'
import modifyRoomInfo from './components/modifyRoomInfo'
import AddCoupon from './components/addCoupon'
import { message } from 'ant-design-vue'
const moment = require('moment');
//优惠策略列表
const columns1 = [
    // 这里定义列表头
    {
        title: '优惠名称',
        dataIndex: 'couponName',
        scopedSlots:{customRender:'couponName'}
    },
    {
        title: '优惠简介',
        dataIndex: 'description',

    },
    {
        title: '折扣',
        dataIndex: 'discount',
    },

    {
        title: '优惠金额',
        dataIndex: 'discountMoney',
    },
    {
        title: 'Action',
        key: 'action',
        scopedSlots: { customRender: 'action' },
    },
];
//订单列表
const columns2 = [
    {
        title: '订单号',
        dataIndex: 'id',
    },
    {
        title: '酒店名',
        dataIndex: 'hotelName',
    },
    {
        title: '房型',
        dataIndex: 'roomType',
        scopedSlots: { customRender: 'roomType' }
    },
    {
        title: '入住时间',
        dataIndex: 'checkInDate',
        scopedSlots: { customRender: 'checkInDate' }
    },
    {
        title: '离店时间',
        dataIndex: 'checkOutDate',
        scopedSlots: { customRender: 'checkOutDate' }
    },
    {
        title: '入住人数',
        dataIndex: 'peopleNum',
    },
    {
        title: '房价',
        dataIndex: 'price',
    },
    {
        title:'状态',
        dataIndex:'orderState',
        scopedSlots: { customRender: 'orderState' },
    },
    {
      title: '操作',
      key: 'action',
      scopedSlots: { customRender: 'action' },
    },
  ];
//房间列表
const columns3 = [
    {
        title: '房型',
        dataIndex: 'roomType',
        key: 'roomType',
    },
    {
        title: '床型',
        dataIndex: 'bedType',
        key: 'bedType',
    },
    {
        title: '数量',
        dataIndex: 'curNum',
        key: 'curNum',
    },
    {
        title: '早餐',
        dataIndex: 'breakfast',
        key: 'breakfast',
        scopedSlots: { customRender: 'breakfast'}
    },
    {
        title: 'WiFi',
        dataIndex: 'wifi',
        key: 'wifi',
        scopedSlots: { customRender: 'wifi'}
    },
    {
        title: '入住人数',
        key: 'peopleNum',
        dataIndex: 'peopleNum',
    },
    {
        title: '房价',
        key: 'price',
        dataIndex: 'price',
        scopedSlots: { customRender: 'price'}
    },
    {
        title: 'Action',
        key: 'action',
        scopedSlots: { customRender: 'action' },
    },
];
export default {
    name: 'manageHotel',
    data(){
        return {
            formLayout: 'horizontal',
            pagination: {},
            columns1,
            columns2,
            columns3,
            colors:['','red','orange','blue','pink','purple','green'],
            form: this.$form.createForm(this, { name: 'manageHotel' }),
            orderState:['已执行','异常'],
            modify:false,
            bizRegion_select:['XiDan','新街口'],
            coupontype:['','生日特惠' ,'多间特惠' ,'满减优惠' ,'限时优惠' ,'合作企业优惠' ,'会员优惠'],
            roomInfo:{roomType:'双床房'}
        }
    },
    components: {
        AddRoomModal,
        AddCoupon,
        modifyRoomInfo
    },
    computed: {
        ...mapGetters([
            'orderList',
            'hotelList',
            'addHotelModalVisible',
            'addRoomModalVisible',
            'activeHotelId',
            'couponVisible',
            'couponList',
            'userInfo',
            'hotelInfo'
        ]),
    },
    async mounted() {
        if(typeof this.userInfo.hotelId!='undefined')
            this.set_activeHotelId(this.userInfo.hotelId);
        await this.getHotelOrders();
        await this.getHotelCoupon();
        await this.getHotelInfo()
    },
    methods: {
        ...mapMutations([
            'set_addHotelModalVisible',
            'set_addRoomModalVisible',
            'set_couponVisible',
            'set_activeHotelId',
            'set_addCouponVisible',
            'set_modifyRoomInfoVisible',
        ]),
        ...mapActions([
            'getHotelList',
            'getHotelOrders',
            'getHotelCoupon',
            'changeOrderState',
            'deleteHotel',
            'deleteOrder',
            'getHotelCoupon',
            'getHotelInfo',
            'updateHotelInfo',
            'deleteHotelCoupon',
            'deleteOrder',
            'judgeOrder'
        ]),
        addCoupon() {
            this.set_addCouponVisible(true)

        },
        deleteCoupon(record){
            let param={
                hotelId:this.activeHotelId,
                couponId:record.id
            };
            this.deleteHotelCoupon(param)
        },
        addRoom() {
            console.log("rooms",this.hotelInfo.rooms);
            this.set_addRoomModalVisible(true)
        },
        modifyRoom(record){
            this.roomInfo=record;
            this.set_modifyRoomInfoVisible(true)
        },
        showCoupon(record) {
            this.set_activeHotelId(record.id);
            this.set_couponVisible(true);
            this.getHotelCoupon()
        },
        removeOrder(record){
            if(record.orderState!=='已预订'){
                const params={
                    orderId:record.id
                };
                this.deleteOrder(params)
            }
            else{
                message.error("操作失败")
            }

        },
        //订单详情
        orderInfo(id){
            let order;
            this.orderList.forEach((item)=>{
                if(item.id === id){
                    order=item
                }
            });
            const h = this.$createElement;
            this.$info({
                title: '订单详情',
                content: h('div', {}, [
                    h('p', '\n'),
                    h('p', '订单id:   '+order.id),
                    h('p', '用户id:   '+order.userId),
                    h('p', '用户名:   '+order.clientName),
                    h('p', '酒店名称:   '+order.hotelName),
                    h('p', '入住时间:   '+order.checkInDate),
                    h('p', '离店时间:   '+order.checkOutDate),
                    h('p', '房间类型:   '+order.roomType),
                    h('p', '房间数量:   '+order.roomNum),
                    h('p', '人数:   '+order.peopleNum),
                    h('p', order.haveChild?'有无儿童:   有':'有无儿童:   无'),
                    h('p', '手机号码:   '+order.phoneNumber),
                    h('p', '总价:   '+order.price+'元'),

                ]),
                onOk() {},
            });
        },
        stateChange(item,index){
            console.log(index);
            if(item.orderState !== this.orderState[index]){
                console.log(this.orderState[index],"orderState");
                const data={
                    orderState:this.orderState[index],
                    orderId:item.id
                };
                this.changeOrderState(data)
            }
        },
        orderJudge(){
            this.judgeOrder()
        },
        order(record){
        },

        modifyInfo(){
            setTimeout(() => {
                this.form.setFieldsValue({
                    'hotelName': this.hotelInfo.name,
                    'address': this.hotelInfo.address,
                    'bizRegion':this.hotelInfo.bizRegion,
                    'hotelStar':this.hotelInfo.hotelStar,
                    'description':this.hotelInfo.description,
                    'phoneNum':this.hotelInfo.phoneNum
                })
            }, 0);
            this.modify = true

        },
        saveModify() {
            this.form.validateFields((err, values) => {
                if (!err) {
                    const data = {
                        id:this.hotelInfo.id,
                        rate:this.hotelInfo.rate,
                        managerId:this.hotelInfo.managerId,
                        name: this.form.getFieldValue('hotelName'),
                        address: this.form.getFieldValue('address'),
                        bizRegion: this.form.getFieldValue('bizRegion'),
                        hotelStar: this.hotelInfo.hotelStar,
                        description: this.form.getFieldValue('description')?this.form.getFieldValue('description'):'',
                        phoneNum: this.form.getFieldValue('phoneNum')?this.form.getFieldValue('phoneNum'):''
                    };
                    this.updateHotelInfo(data).then(()=>{
                        this.modify = false
                    })
                }
            });
        },
        cancelModify() {
            this.modify = false
        },

        bizRegionChange(v){

        }
    }
}
</script>
<style scoped lang="less">
    .manageHotel-wrapper {
        padding: 50px;
        .chart {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-top: 20px
        }
    }
</style>
<style lang="less">
    .manageHotel-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style lang="less">
    .stateBox{
        display: flex;
        width: 80%;
        margin: auto auto;
        justify-content:space-between;
        align-items: center;
    }
    .state:hover{
        color:black;
    }
    .state:active{
        color: red;
    }
</style>