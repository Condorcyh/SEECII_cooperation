<template>
    <div class="manageWebsite-wrapper">
        <a-tabs>
            <a-tab-pane tab="酒店信息" key="1">
                <a-table
                        :columns="columns1"
                        :dataSource="hotelList"
                        bordered
                >
                    <span slot="action" slot-scope="record">
                        <a-button type="primary" size="small" @click="showCoupon(record)">优惠策略</a-button>
                    </span>
                </a-table>
            </a-tab-pane>
            <a-tab-pane tab="订单管理" key="2">
                <a-table
                        :columns="columns2"
                        :dataSource="allOrders"
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
            <a-tab-pane tab="网站优惠策略" key="3">
                <div style="width: 100%; text-align: right; margin:20px 0">
                    <a-button  type="primary" @click="addCoupon"><a-icon type="plus"/>添加优惠策略</a-button>
                </div>

                <a-tag  color="blue">满减优惠</a-tag>
                <a-tag  color="pink">限时优惠</a-tag>
                <a-tag  color="green">VIP优惠</a-tag>
                <div style="margin-top: 10px;">
                    <a-table :dataSource="websiteCoupons" :columns="columns3" bordered>
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
            <a-tab-pane tab="会员管理" key="4">
                <div style="">
                <div style="margin-top: 20px;">
                    <span class="VIPfont">VIP1:</span>
                    <a-slider  v-model="vipRules.vip1" :marks="marks_1" :step="10"  :min="0" :max="200" @afterChange="onAfterChange" />
                </div>
                <div style="margin-top: 50px;">
                    <span class="VIPfont">VIP2:</span>
                    <a-slider  v-model="vipRules.vip2" :marks="marks_2" :step="10" :min="200" :max="500" @afterChange="onAfterChange"  />
                </div>
                <div style="margin-top: 50px;">
                    <span class="VIPfont">VIP3:</span>
                    <a-slider   v-model="vipRules.vip3" :marks="marks_3" :step="10" :min="500" :max="1000" @afterChange="onAfterChange"/>
                </div>
                <div style="margin-top: 50px;">
                    <span class="VIPfont">VIP4:</span>
                    <a-slider  v-model="vipRules.vip4" :marks="marks_4" :step="10" :min="1000" :max="2000" @afterChange="onAfterChange" />
                </div>
                </div>
            </a-tab-pane>
            <div class="creditBtn">
                <a-button type="default"
                          @click="creditChargeVisibleMethod()" >
                    信用充值
                </a-button>
            </div>
        </a-tabs>


        <AddCoupon></AddCoupon>
        <Coupon></Coupon>
        <CreditCharge></CreditCharge>
    </div>
</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'

   import AddCoupon from './components/addCoupon'
    import CreditCharge from "./components/creditCharge";
    import { message } from 'ant-design-vue'
    import Coupon from "./components/coupon";
    const moment = require('moment');
    const columns1 = [
        {
            title: '酒店名',
            dataIndex: 'name',
        },
        {
            title: '商圈',
            dataIndex: 'bizRegion',
        },
        {
            title: '地址',
            dataIndex: 'address',
        },
        {
            title: '酒店星级',
            dataIndex: 'hotelStar'
        },
        {
            title: '评分',
            dataIndex: 'rate',
        },
        {
            title: '简介',
            dataIndex: 'description',
        },
        {
            title: '操作',
            key: 'action',
            scopedSlots: { customRender: 'action' },
        },
    ];
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
    const columns3 = [
        // 这里定义列表头
        {
            title: '优惠名称',
            dataIndex: 'couponName',
            width: '30%',
            scopedSlots:{customRender:'couponName'}
        },
        {
            title: '优惠简介',
            dataIndex: 'description',
            width: '25%',
        },
        {
            title: '折扣',
            dataIndex: 'discount',
            width: '20%',
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
    export default {
        name: 'manageWebsite',
        data(){
            return {
                formLayout: 'horizontal',
                pagination: {},
                columns1,
                columns2,
                columns3,
                colors:['','red','orange','blue','pink','purple','green'],
                form: this.$form.createForm(this, { name: 'manageWebsite' }),
                orderState:['已执行','异常'],
                disabled:true,

                marks_1:{
                    0:'',
                    100: {style:{color:'#000'}, label:100},
                    150:{style:{color:'#000'}, label:150},
                    200: '',
                },
                marks_2:{
                    200:'',
                    300:{style:{color:'#000'}, label:300},
                    400: {style:{color:'#000'}, label:400},
                    500: '',
                },
                marks_3:{
                    500:'',
                    600:{style:{color:'#000'}, label:600},
                    700: {style:{color:'#000'}, label:700},
                    800: {style:{color:'#000'}, label:800},
                    900:{style:{color:'#000'}, label:900},
                    1000:''
                },
                marks_4:{
                    1000: '',
                    1200:{style:{color:'#000'}, label:1200},
                    1500:{style:{color:'#000'}, label:1500},
                    1800:{style:{color:'#000'}, label:1800},
                    2000:'',


                }
            }
        },
        components: {
            Coupon,
            AddCoupon,
            CreditCharge
        },
        computed: {
            ...mapGetters([
                'hotelList',
                'addHotelModalVisible',
                'addRoomModalVisible',
                'activeHotelId',
                'couponVisible',
                'allOrders',
                'couponList',
                'websiteHotelId',
                'websiteCoupons',
                'vipRules',
                'creditChargeVisible'
            ]),
        },
        async mounted() {
            await this.getHotelList();
            await this.getAllOrders();
            await this.getWebsiteCoupons();
            await this.getVIPRules()

        },
        methods: {
            ...mapMutations([
                'set_addHotelModalVisible',
                'set_addRoomModalVisible',
                'set_couponVisible',
                'set_activeHotelId',
                'set_addCouponVisible',
                'set_websiteHotelId',
                'set_creditChargeVisible'
            ]),
            ...mapActions([
                'getHotelList',
                'getAllOrders',
                'getWebsiteCoupons',
                'changeOrderState_',
                'deleteHotel',
                'deleteOrder_',
                'getHotelCoupon',
                'getVIPRules',
                'modifyVIPRules',
                'deleteWebsiteCoupon'
            ]),
            //点击信用充值时触发
            creditChargeVisibleMethod(){
                this.set_creditChargeVisible(true)
            },

            addCoupon() {
                this.set_addCouponVisible(true)

            },
            showCoupon(record) {
                this.set_activeHotelId(record.id);
                this.set_couponVisible(true);
                this.getHotelCoupon()
            },
            deleteCoupon(record){
                let param={
                    hotelId:0,
                    couponId:record.id
                };
                this.deleteWebsiteCoupon(param)
            },

            removeOrder(record){
                if(record.orderState !=='已预订'){
                    const params={
                        orderId:record.id
                    };
                    this.deleteOrder_(params)
                }
                else{
                    message.error("操作失败")
                }

            },
            //订单详情
            orderInfo(id){
                let order;
                this.allOrders.forEach((item)=>{
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
            //改变订单状态
            stateChange(item,index){
                console.log(index);
                if(item.orderState !== this.orderState[index]){
                    console.log(this.orderState[index],"orderState");
                    const data={
                        orderState:this.orderState[index],
                        orderId:item.id
                    };
                    this.changeOrderState_(data)
                }
            },
            //修改VIP规则
            modifyVIP(e){
                if(e){
                    this.disabled=false
                }else {
                    this.disabled=true;
                    this.modifyVIPRules(this.vipRules)
                }
            },
            //VIP成长值条改变时调用
            onAfterChange(value) {
                this.modifyVIPRules(this.vipRules)

            },
        }
    }
</script>
<style scoped lang="less">
    .manageWebsite-wrapper {
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
    .manageWebsite-wrapper {
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
    .creditBtn{
        position: absolute;
        top:5px;
        right: 10px;
    }
    .VIPfont{
        font-size: 20px;
        font-weight: 700;
        margin-top: 10px;
        margin-left: 10px;
    }
    .bar{
        top: 20px;
    }
</style>