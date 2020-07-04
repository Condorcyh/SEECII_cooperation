<template>
    <div class="room-list">
        <div class="filter">

        </div>
        <div class="list">
            <a-table
                :columns="columns"
                :dataSource="rooms"
            >
                <span slot="price" slot-scope="text">
                    <span>￥{{ text }}</span>
                </span>
                <span slot="breakfast" slot-scope="text">
                        <span>{{text==1?'有':'无' }}</span>
                    </span>
                <span slot="wifi" slot-scope="text">
                        <span>{{text==1?'有':'无' }}</span>
                    </span>
                <span slot="action" slot-scope="text, record">
                    <a-button type="primary" @click="order(record)">预定</a-button>
                </span>
            </a-table>
        </div>
        <OrderModal></OrderModal>
    </div>
</template>
<script>
import { mapGetters, mapActions, mapMutations } from 'vuex'
import OrderModal from './orderModal'
const columns = [
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
      title: '最多入住人数',
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
    name:'roomList',
    props: {
        rooms: {
            type: Array
        }
    },
    data() {
        return {
            columns,
        }
    },
    components: {
        OrderModal
    },
    computed: {
        ...mapGetters([
            'orderModalVisible'
        ])
    },
    methods: {
        ...mapMutations([
            'set_orderModalVisible',
            'set_currentOrderRoom'
        ]),
        ...mapActions([

        ]),
        order(record) {
            this.set_currentOrderRoom(record);
            this.set_orderModalVisible(true)
        }
    }
}
</script>