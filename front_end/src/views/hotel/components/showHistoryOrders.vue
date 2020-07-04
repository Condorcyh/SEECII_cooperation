<template>
    <AModal
        :visible="isShowHistoryOrders"
        title="历史订单"
        cancelText="OK"
        okText="Over"
        @cancel="handleSubmit"
        @ok="handleSubmit">
        <a-table
            :columns="columns"
            :dataSource="hotelOrderList">
            <span slot="price" slot-scope="text">
                        <span>￥{{ text }}</span>
                    </span>
            <span slot="roomType" slot-scope="text">
                        <span v-if="text === 'BigBed'">大床房</span>
                        <span v-if="text === 'DoubleBed'">双床房</span>
                        <span v-if="text === 'Family'">家庭房</span>
                    </span>
            <a-tag slot="orderState" color="blue" slot-scope="text">
                {{ text }}
            </a-tag>
        </a-table>
    </AModal>
</template>

<script>
    import {mapActions, mapGetters, mapMutations} from 'vuex';
    const columns = [
        {
            title: '订单号',
            dataIndex: 'id',
            key:'id'
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
    ];
    export default {
        name:'',
        data() {
            return{
                isOrdered:false,
                columns,
            }
        },
        computed:{
            ...mapGetters([
                'isShowHistoryOrders',
                'hotelOrderList'
            ])

        },
        methods:{
            ...mapMutations([
                'set_isShowHistoryOrders'
            ]),
            ...mapActions([

            ]),
            show(){
                console.log(this.userOrderList);
                console.log(this.hotel)
            },
            showHistoryOrders(){
                console.log("订单展示");
                this.isShowHistoryOrders = true
            },
            handleSubmit(){
                this.set_isShowHistoryOrders(false)
            }
        },
    }
</script>
