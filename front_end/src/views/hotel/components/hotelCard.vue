<template class="main">
  <a-card hoverable class="hotelCard ant-col-xs-7 ant-col-lg-5 ant-col-xxl-3">
    <img
      alt="example"
      src="@/assets/cover.jpeg"
      slot="cover"
      referrerPolicy="no-referrer"
    />
    <a-tooltip :title="hotel.title" placement="top">
      <a-card-meta :title="hotel.name">
      <template slot="description">
        <a-rate style="font-size: 15px" :value="stars[hotel.hotelStar]" disabled allowHalf/> {{hotel.rate}}分
        <br>
        <a-tag v-show="isOrdered" color="orange" @click="show">曾预订</a-tag>
        <a-tag v-show="isOrdered" color="green" @mouseenter="showHistoryOrders">历史订单</a-tag>
      </template>
    </a-card-meta>
    </a-tooltip>
    <ShowHistoryOrders ></ShowHistoryOrders>
  </a-card>
</template>
<script>
  import {mapActions, mapGetters, mapMutations} from 'vuex'
  import ShowHistoryOrders from './showHistoryOrders'
  export default {
    name:'',
    props: {
      hotel: {}
    },
    components:{
      ShowHistoryOrders
    },
    data() {
      return{
          stars:{
              Five:5,
              Four:4,
              Three:3,
              Two:2,
              One:1
          },
        orderList:[],
        isOrdered: false,
      }
    },
    computed:{
      ...mapGetters([
        'isShowHistoryOrders',
        'userOrderList',
        'hotelOrderList',
        'hotelList'
      ])

    },
    async mounted() {
      console.log("hotel",this.hotel);
      await this.getUserOrders();
      for(let i = 0; i < this.userOrderList.length; i++){
        if(this.userOrderList[i].hotelId === this.hotel.id){
          console.log("hotel.id",this.hotel.id);
          this.isOrdered = true;
          break
        }
      }
      for(let i=0;i<this.hotelList.length;i++){
        if(this.isOrdered && this.hotel.id===this.hotelList[i].id){
          this.hotelList[i].isOrdered=true;
        }
        else if(!this.isOrdered&&this.hotel.id===this.hotelList[i].id){
          this.hotelList[i].isOrdered=false;
        }
      }
      console.log("htoel",this.hotelList);
    },
    methods:{
      ...mapMutations([
        'set_isShowHistoryOrders',
              'set_hotelOrderList'
      ]),
      ...mapActions([
        'getUserOrders'
      ]),
      show(){
        console.log(this.userOrderList);
        console.log(this.hotel)
      },
      //显示历史订单
      showHistoryOrders(){
        this.set_hotelOrderList([]);
        for(let i = 0; i < this.userOrderList.length; i++){
          if(this.userOrderList[i].hotelId === this.hotel.id){
            this.isOrdered = true;
            this.hotelOrderList.push(this.userOrderList[i]);
          }
        }
        this.set_isShowHistoryOrders(true);
      }
    },
  }
</script>
<style scoped lang="less">
    .hotelCard {
        margin: 10px 10px;
        min-width: 180px;
        max-height: 350px;
        img {
          height: 250px;
        }
    }
</style>

<style lang="less">
.hotelCard{
  .ant-card-body{
    padding: 12px
  }
}

.ant-card-hoverable:hover {
  box-shadow: 0 2px 8px rgba(0,0,0,0.4)
}

</style>