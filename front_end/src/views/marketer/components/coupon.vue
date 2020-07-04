<template>
    <div>
        <a-modal
                :visible="couponVisible"
                title="优惠策略"
                width="900px"
                :footer="null"
                @cancel="cancel"
        >
            <!-- TODO 模拟框内容区域-->
            <!-- 这里是模态框内容区域，请编写列表代码与添加策略按钮 -->
<!--            <div class="button">-->
<!--                <a-button  type="primary" @click="addCoupon"><a-icon type="plus"/>添加优惠策略</a-button>-->
<!--            </div>-->
            <a-table :dataSource="couponList" :columns="columns" bordered>
                <a-tag slot="couponName" slot-scope="couponName,record" :color="colors[record.couponType]">{{couponName}}</a-tag>
            </a-table>

        </a-modal>

    </div>
</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    const columns = [
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
    ];
    export default {
        name: 'coupon',
        data() {
            return {
                columns,
                colors:['','red','orange','blue','pink','purple','green']
            }
        },
        components: {

        },
        computed: {
            ...mapGetters([
                'couponVisible',
                'couponList',
            ])
        },
        methods: {
            ...mapMutations([
                'set_addCouponVisible',
                'set_couponVisible',
            ]),
            ...mapActions([
                'getHotelCoupon'
            ]),
            cancel() {
                this.set_couponVisible(false)
            },
            addCoupon() {
                this.set_addCouponVisible(true);
                this.set_couponVisible(false)
            },
        },
    }
</script>
<style scoped>
    .button{
        width: 100%;
        text-align: right;
        margin-top: 20px;
        margin-bottom: 20px;
    }
</style>