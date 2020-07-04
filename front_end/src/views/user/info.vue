<template>
    <div class="info-wrapper">
        <a-tabs>
            <a-tab-pane tab="我的信息" key="1">
                <a-form :form="form" style="margin-top: 30px">
                    <a-form-item label="用户名" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1  }">
                        <a-input
                            placeholder="请填写用户名"
                            v-decorator="['userName', { rules: [{ required: true, message: '请输入用户名' }] }]"
                            v-if="modify"
                        />
                        <span v-else>{{ userInfo.userName }}</span>
                    </a-form-item>
                    <a-form-item label="用户ID" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                        <span>{{ userInfo.id}}</span>
                    </a-form-item>
                    <a-form-item label="邮箱" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                        <span>{{ userInfo.email }}</span>
                    </a-form-item>

                    <a-form-item label="手机号" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                        <a-input
                            placeholder="请填写手机号"
                            v-decorator="['phoneNumber', { rules: [{ required: true, message: '请输入手机号' }] }]"
                            v-if="modify"
                        />
                        <span v-else>{{ userInfo.phoneNumber}}</span>
                    </a-form-item>
                    <a-form-item label="生日" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1  }">
                        <a-input
                                placeholder="格式:1998-07-14(选填)"
                                v-decorator="['birthday', { rules: [{ required: false, message: '请输入正确的生日格式',pattern:'((((19|20)\\d{2})-(0?(1|[3-9])|1[012])-(0?[1-9]|[12]\\d|30))|(((19|20)\\d{2})-(0?[13578]|1[02])-31)|(((19|20)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|((((19|20)([13579][26]|[2468][048]|0[48]))|(2000))-0?2-29))$' }] }]"
                                v-if="modify"
                        />
                        <span v-else>{{ userInfo.birthday }}</span>
                    </a-form-item>
                    <a-form-item label="信用值" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                        <span>{{ userInfo.credit }}</span>
                    </a-form-item>
                    <a-form-item label="VIP等级" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                        <span>{{ userInfo.vipLevel }}</span>
                    </a-form-item>
                    <a-form-item label="企业名称" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1  }">
                        <a-input
                                placeholder="请填写企业名称(选填)"
                                v-decorator="['enterprise', { rules: [{ required: false, message: '请输入企业名称' }] }]"
                                v-if="modify"
                        />
                        <span v-else>{{ userInfo.enterprise }}</span>
                    </a-form-item>
                    <a-form-item label="密码" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }" v-if="modify">
                        <a-input
                            placeholder="请输入新密码"
                            v-decorator="['password', { rules: [{ required: true, message: '请输入新密码' }] }]"
                        />
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
            <a-tab-pane tab="我的订单" key="2">
                <a-table
                    :columns="columns"
                    :dataSource="userOrderList"
                    bordered
                >
                    <span slot="price" slot-scope="text">
                        <span>￥{{ text }}</span>
                    </span>
                    <span slot="roomType" slot-scope="text">
                        <span v-if="text === 'BigBed'">大床房</span>
                        <span v-if="text === 'DoubleBed'">双床房</span>
                        <span v-if="text === 'Family'">家庭房</span>
                        <span v-if="text === 'SingleBed'">单人房</span>
                        <span v-if="text === 'Theme'">主题套房</span>
                        <span v-if="text === 'Luxury'">豪华套房</span>
                    </span>
                    <a-tag slot="orderState" color="blue" slot-scope="text">
                        {{ text }}
                    </a-tag>
                    <span slot="action" slot-scope="record">
                        <a-button type="primary" size="small" @click="orderInfo(record.id)">查看</a-button>
                        <a-divider type="vertical" v-if="record.orderState==='已执行'"></a-divider>
                        <a-button size="small" @click="commentTheRecord(record)" v-show="record.orderState==='已执行'">评论</a-button>

                        <a-modal
                            :visible="visible"
                            title="评论"
                            cancelText=""
                            okText=""
                            @cancel="cancel"
                            @ok="handleSubmit">
                            <a-form-item label="评分" v-bind="formItemLayout">
                                <a-select style="width: 70px;" v-model="commentRate">
                                <a-select-option value="1">1</a-select-option>
                                <a-select-option value="2">2</a-select-option>
                                <a-select-option value="3">3</a-select-option>
                                <a-select-option value="4">4</a-select-option>
                                <a-select-option value="5">5</a-select-option>
                                </a-select>
                            </a-form-item>
                            <a-form-item v-bind="formItemLayout" label="用户评论">
                                <a-textarea
                                        :rows="4"
                                        v-model="commentValue"
                                        placeholder="请输入评论">
                            </a-textarea>
                            </a-form-item>
                        </a-modal>
                        <a-divider type="vertical" v-if="record.orderState === '已预订'"></a-divider>
                        <a-popconfirm
                            title="你确定撤销该笔订单吗？"
                            @confirm="confirmCancelOrder(record.id)"
                            @cancel="cancelCancelOrder"
                            okText="确定"
                            cancelText="取消"
                            v-if="record.orderState === '已预订'"
                        >
                            <a-button type="danger" size="small">撤销</a-button>
                        </a-popconfirm>

                    </span>
                </a-table>
            </a-tab-pane>
        </a-tabs>
    </div>
</template>
<script>
import { mapGetters, mapActions } from 'vuex'
const moment = require('moment');
//订单列表
const columns = [
    {
        title: '订单号',
        dataIndex: 'id',
        key:'id'
    },
    {
        title: '酒店名',
        dataIndex: 'hotelName',
        key:'hotelName'
    },
    {
        title: '房型',
        dataIndex: 'roomType',
        key:'roomType',
        scopedSlots: { customRender: 'roomType' }
    },
    {
        title: '入住时间',
        dataIndex: 'checkInDate',
        key:'checkInDate',
        scopedSlots: { customRender: 'checkInDate' }
    },
    {
        title: '离店时间',
        dataIndex: 'checkOutDate',
        key:'checkOutDate',
        scopedSlots: { customRender: 'checkOutDate' }
    },
    {
        title: '入住人数',
        dataIndex: 'peopleNum',
        key:'peopleNum'
    },
    {
        title: '房价',
        dataIndex: 'price',
        key:'price',
        scopedSlots: { customRender: 'price' }
    },
    {
        title: '状态',
        filters: [{ text: '已预订', value: '已预订' }, { text: '已撤销', value: '已撤销' }, { text: '已入住', value: '已入住' }],
        onFilter: (value, record) => record.orderState.includes(value),
        dataIndex: 'orderState',
        key:'orderState',
        scopedSlots: { customRender: 'orderState' },
    },
    {
      title: '操作',
      key: 'action',
      scopedSlots: { customRender: 'action' },
    },

  ];
export default {
    name: 'info',
    data(){
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
            modify: false,
            formLayout: 'horizontal',
            pagination: {},
            columns,
            data: [],
            form: this.$form.createForm(this, { name: 'coordinated' }),
            visible:false,
            commentValue:'',
            commentRate:3,
            currentOrder:{}
        }
    },
    components: {
    },
    computed: {
        ...mapGetters([
            'userInfo',
            'userOrderList',
            'iconChoose'
        ])
    },
    async mounted() {
        await this.getUserInfo();
        await this.getUserOrders()
    },
    methods: {
        ...mapActions([
            'getUserInfo',
            'getUserOrders',
            'updateUserInfo',
            'cancelOrder',
           'addComment'
        ]),
        //订单详情
        orderInfo(id){
            let order;
            this.userOrderList.forEach((item)=>{
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
                    h('p', '用户名:   '+order.userName),
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
        //保存更改
        saveModify() {
            this.form.validateFields((err, values) => {
                if (!err) {
                    const data = {
                        userName: this.form.getFieldValue('userName'),
                        phoneNumber: this.form.getFieldValue('phoneNumber'),
                        password: this.form.getFieldValue('password'),
                        birthday: this.form.getFieldValue('birthday')?this.form.getFieldValue('birthday'):null,
                        enterprise: this.form.getFieldValue('enterprise')?this.form.getFieldValue('enterprise'):null
                    };
                    this.updateUserInfo(data).then(()=>{
                        this.modify = false
                    })
                }
            });
        },
        //修改个人信息
        modifyInfo() {
            setTimeout(() => {
                this.form.setFieldsValue({
                    'userName': this.userInfo.userName,
                    'phoneNumber': this.userInfo.phoneNumber,
                    'birthday':this.userInfo.birthday,
                    'enterprise':this.userInfo.enterprise,
                    'password':this.userInfo.password
                })
            }, 0);
            this.modify = true
        },
        cancelModify() {
            this.modify = false
        },
        confirmCancelOrder(orderId){
            this.cancelOrder(orderId)
        },
        cancelCancelOrder() {

        },
        commentTheRecord(record){
            this.currentOrder=record;
            this.visible = true
        },
        cancel(){
            this.commentValue = '';
            this.commentRate=3;
            this.visible = false;
            let date = moment(new Date()).add('year',0).format("YYYY-MM-DD");
        },
        handleSubmit(){
            console.log(this.userInfo);
            let date = moment(new Date()).add('year',0).format("YYYY-MM-DD");
            var data = {
                userId:this.userInfo.id,
                userName: this.userInfo.userName,
                userAvatar: this.userInfo.avatar,
                rate: this.commentRate,
                comment: this.commentValue,
                time: date,
                hotelId:this.currentOrder.hotelId
            };
            console.log(data);
            this.addComment(data);
            this.commentValue = '';
            this.commentRate = 3;
            this.visible = false
        },


    }
}
</script>
<style scoped lang="less">
    .info-wrapper {
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
    .info-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style lang="less">

</style>