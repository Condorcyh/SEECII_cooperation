<template>
    <div class="manageUser-wrapper">
        <a-tabs>
            <a-tab-pane tab="账户管理" key="1">
                <div style="width: 100%; text-align: right; margin:20px 0">
                    <a-button type="primary" @click="addManager"><a-icon type="plus" />添加用户</a-button>
                </div>
                <a-table
                    :columns="columns"
                    :dataSource="managerList"
                    bordered
                >
                    <span slot="price" slot-scope="text">
                        <span>￥{{ text }}</span>
                    </span>
                    <span slot="action" slot-scope="text, record">
                        <a-button type="primary" size="small" @click="modifyUserInfo(record)">修改信息</a-button>
                        <a-divider type="vertical"></a-divider>
                        <a-popconfirm
                                title="确定想删除该用户吗？"
                                @confirm="removeUser(record)"
                                okText="确定"
                                cancelText="取消"
                        >
                            <a-button type="danger" size="small">删除用户</a-button>
                        </a-popconfirm>
                    </span>
                </a-table>
            </a-tab-pane>
            <a-tab-pane tab="酒店管理" key="2">
                <div style="width: 100%; text-align: right; margin:20px 0">
                    <a-button type="primary" @click="addHotel"><a-icon type="plus" />添加酒店</a-button>
                </div>
                <a-table
                        :columns="columns1"
                        :dataSource="hotelList"
                        bordered
                >
                    <span slot="action" slot-scope="record">

                        <a-button type="primary" size="small" @click="showCoupon(record)">优惠策略</a-button>
                        <a-divider type="vertical"></a-divider>
                        <a-popconfirm
                                title="确定想删除该酒店吗？"
                                @confirm="removeHotel(record)"
                                okText="确定"
                                cancelText="取消"
                        >
                            <a-button type="danger" size="small">删除酒店</a-button>
                        </a-popconfirm>
                    </span>
                </a-table>
            </a-tab-pane>
        </a-tabs>
        <AddManagerModal></AddManagerModal>
        <AddHotelModal></AddHotelModal>
        <Coupon></Coupon>
        <modify-user-info :user-info="userInfo"></modify-user-info>
    </div>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
import AddManagerModal from './components/addManagerModal'
import AddHotelModal from './components/addHotelModal'
import Coupon from "./components/coupon";
import modifyUserInfo from "./components/modifyUserInfo";
//用户列表
const columns = [
    {
        title: '用户邮箱',
        dataIndex: 'email',
    },
    {
        title: '用户名',
        dataIndex: 'userName',
    },
    {
        title: '用户类型',
        dataIndex: 'userType',
    },
    {
        title: '用户密码',
        dataIndex: 'password',
    },
    {
        title: '用户手机号',
        dataIndex: 'phoneNumber',
    },
    {
        title: '信用值',
        dataIndex: 'credit',
    },
    {
      title: '操作',
      key: 'action',
      scopedSlots: { customRender: 'action' },
    },
  ];
//酒店列表
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
export default {
    name: 'manageHotel',
    data(){
        return {
            formLayout: 'horizontal',
            pagination: {},
            columns,
            data: [],
            form: this.$form.createForm(this, { name: 'manageUser' }),
            columns1,
            userInfo:{}
        }
    },
    components: {
        AddManagerModal,
        AddHotelModal,
        Coupon,
        modifyUserInfo
    },
    computed: {
        ...mapGetters([
            'addManagerModalVisible',
            'addHotelModalVisible',
            'addRoomModalVisible',
            'managerList',
            'hotelList',
            'orderList',
            'couponVisible'
        ])
    },
    async mounted() {
        await this.getHotelList();
        await this.getAllOrders();
        await this.getManagerList()
    },
    methods: {
        ...mapMutations([
            'set_addHotelModalVisible',
            'set_addManagerModalVisible',
            'set_activeHotelId',
            'set_addRoomModalVisible',
            'set_couponVisible',
            'set_modifyUserInfoVisible'
        ]),
        ...mapActions([
            'getManagerList',
            'deleteHotel',
            'getHotelCoupon',
            'getHotelList',
            'getAllOrders',
            'deleteUser'
        ]),
        //修改用户信息
        modifyUserInfo(record){
            this.userInfo=record;
            this.set_modifyUserInfoVisible(true)

        },
        //删除用户
        removeUser(record){
            let param={
                userId:record.id
            };
            this.deleteUser(param)
        },
        //添加工作人员或营销人员
        addManager(){
            this.set_addManagerModalVisible(true)
        },
        //添加酒店
        addHotel() {
            this.set_addHotelModalVisible(true)
        },
        //显示优惠策略
        showCoupon(record) {
            this.set_activeHotelId(record.id);
            this.set_couponVisible(true);
            this.getHotelCoupon()
        },
        //删除酒店
        removeHotel(record){
            let param={
                hotelId:record.id
            };
            this.deleteHotel(param)
        },
    }
}
</script>
<style scoped lang="less">
    .manageUser-wrapper {
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
    .manageUser-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style lang="less">

</style>