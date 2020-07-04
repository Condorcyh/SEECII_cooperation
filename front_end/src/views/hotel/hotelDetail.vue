<template>
    <a-layout style="background-color: rgb(245, 248, 255)">
        <a-layout-content>
            <div class="hotelDetailCard">
                <h1>
                    {{ currentHotelInfo.title }}
                </h1>
                <div class="hotel-info">
                    <a-card style="width: 240px">
                        <img
                            alt="example"
                            src="@/assets/cover.jpeg"
                            slot="cover"
                            referrerPolicy="no-referrer"
                            />
                    </a-card>
                    <div class="info">
                        <div class="items" v-if="currentHotelInfo.name">
                            <span class="label">酒店名称：</span>
                            <span class="value">{{ currentHotelInfo.name }}</span>
                        </div>
                        <div class="items" v-if="currentHotelInfo.address">
                            <span class="label">地址</span>
                            <span class="value">{{ currentHotelInfo.address }}</span>
                        </div>
                        <div class="items" v-if="currentHotelInfo.rate">
                            <span class="label">评分:</span>
                            <span class="value">{{ currentHotelInfo.rate }}</span>
                        </div>
                        <div class="items" v-if="currentHotelInfo.hotelStar">
                            <span class="label">星级:</span>
                            <a-rate style="font-size: 15px" :value="stars[currentHotelInfo.hotelStar]" disabled allowHalf/>
                        </div>
                        <div class="items" v-if="currentHotelInfo.description">
                            <span class="label">酒店简介:</span>
                            <span class="value">{{ currentHotelInfo.description }}</span>
                        </div>
                    </div>
                </div>
                <a-divider></a-divider>
                <a-tabs>
                    <a-tab-pane tab="房间信息" key="1">
                        <RoomList :rooms="currentHotelInfo.rooms"></RoomList>
                    </a-tab-pane>
                    <a-tab-pane tab="酒店详情" key="2">
                        <div>
                            <span style="display:block;font-size: 15px;font-weight: 600;margin-left: 20px;">酒店详情:</span>
                            <div class="description">   {{currentHotelInfo.description}}</div>
                        </div>
                        <div style="margin-top: 20px;">
                            <span style="display:block;font-size: 15px;font-weight: 600;margin-left: 20px;">优惠券策略:</span>
                            <div style="margin-top: 10px;">
                            <a-tag  color="red" style="margin-left: 20px;">生日优惠</a-tag>
                            <a-tag  color="orange">多间优惠</a-tag>
                            <a-tag  color="blue">满减优惠</a-tag>
                            <a-tag  color="pink">限时优惠</a-tag>
                            <a-tag  color="purple">合作企业优惠</a-tag>
                            <a-tag  color="green">VIP优惠</a-tag>
                            </div>
                            <div style="margin-top: 10px;">
                            <a-table :dataSource="couponList" :columns="columns" bordered>
                                <a-tag slot="couponName" slot-scope="couponName,record" :color="colors[record.couponType]">{{couponName}}</a-tag>
                            </a-table>
                            </div>

                        </div>
                    </a-tab-pane>
                    <a-tab-pane tab="用户评论" key="3">
                        <Comment :comments="userComment"></Comment>
                    </a-tab-pane>
                </a-tabs>
            </div>
        </a-layout-content>
    </a-layout>
</template>
<script>
import { mapGetters, mapActions, mapMutations } from 'vuex'
import RoomList from './components/roomList'
import Comment from "./components/comment"
const columns = [
    // 这里定义列表头
    {
        title: '优惠名称',
        dataIndex: 'couponName',
        width: '30%',
        scopedSlots:{customRender:'couponName'}
    },
    {
        title: '折扣',
        dataIndex: 'discount',
        width: '20%',
    },
    {
        title: '优惠简介',
        dataIndex: 'description',
        width: '25%',
    },
    {
        title: '优惠金额',
        dataIndex: 'discountMoney',
    },
];
export default {

    name: 'hotelDetail',
    components: {
        Comment,
        RoomList,
    },
    data() {
        return {
            columns,
            colors:['','red','orange','blue','pink','purple','green'],
            stars:{
                Five:5,
                Four:4,
                Three:3,
                Two:2,
                One:1
            }
        }
    },
    computed: {
        ...mapGetters([
            'currentHotelInfo',
            'userComment',
            'hotelList',
            'couponList'
        ])
    },
    async mounted() {
        this.set_currentHotelId(Number(this.$route.params.hotelId));
        this.getHotelById();
        this.set_activeHotelId(Number(this.$route.params.hotelId));
        this.getHotelCoupon();
        this.getCommentsById()

    },
    beforeRouteUpdate(to, from, next) {
        this.set_currentHotelId(Number(to.params.hotelId));
        this.getHotelById();
        this.set_activeHotelId(Number(this.$route.params.hotelId));
        this.getHotelCoupon();
        this.getCommentsById();
        next()
    },
    methods: {
        ...mapMutations([
            'set_currentHotelId',
            'set_activeHotelId'
        ]),
        ...mapActions([
            'getHotelById',
            'getCommentsById',
            'getHotelCoupon',
            'updateCommentLike',
            'updateCommentDislike'
        ])
    }
}
</script>
<style scoped lang="less">
    .hotelDetailCard {
        padding: 50px 50px;
    }
    .hotel-info {
        display: flex;
        align-items: stretch;
        justify-content: flex-start;
        .info{
            padding: 10px 0;
            display: flex;
            flex-direction: column;
            margin-left: 20px;
            .items {
                display: flex;
                align-items: center;
                margin-bottom: 10px;
                .label{
                    margin-right: 10px;
                    font-size: 18px;
                }
                .value {
                    margin-right: 15px
                }
            }
        }
    }
    .description{
        font-family: 宋体;
        font-size: 15px;
        font-weight: 900;
        min-height: 60px;
        margin: 5px auto;
        width: 90%;
        letter-spacing:1px;
        overflow: visible;

    }
</style>