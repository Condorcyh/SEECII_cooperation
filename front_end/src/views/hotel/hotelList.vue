<template>
  <div class="hotelList">
    <a-layout style="background-color: rgb(245, 248, 255);">

        <a-layout-content style="min-width: 800px">
            <filter-box></filter-box>
          <a-spin :spinning="hotelListLoading">
            <div class="card-wrapper">
                <HotelCard :hotel="item" v-for="item in getPageHotelList" :key="item.index" @click.native="jumpToDetails(item.id)"></HotelCard>
                <div v-for="item in emptyBox" :key="item.name" class="emptyBox ant-col-xs-7 ant-col-lg-5 ant-col-xxl-3">
                </div>
            </div>
              <a-pagination class="pagination" showQuickJumper :total="hotelList.length"  :defaultPageSize="8" @change="pageChange"></a-pagination>
          </a-spin>
      </a-layout-content>
    </a-layout>
  </div>
</template>
<script>
import HotelCard from './components/hotelCard'
import filterBox from "./components/filterBox";
import { mapGetters, mapActions, mapMutations } from 'vuex'

export default {
  name: 'home',
  components: {
    HotelCard,filterBox
  },
  data(){
    return{
      emptyBox: [{ name: 'box1' }, { name: 'box2'}, {name: 'box3'}]
    }
  },
  async mounted() {
    await this.addRegionFilter()
  },
  computed: {
    ...mapGetters([
      'hotelList',
      'hotelListLoading',
        'filterHotelList',
        'hotelListParams'
    ]),
      getPageHotelList:function () {
          let pageNo = this.hotelListParams.pageNo;
          let pageSize = this.hotelListParams.pageSize;
          let start = pageNo*pageSize;
          let end = start + pageSize;
          let pageHotelList = [];
          if(end<=this.hotelList.length){
              pageHotelList=this.hotelList.slice(start,end)
          }
          else if(start<this.hotelList.length){
              pageHotelList=this.hotelList.slice(start)
          }
          return pageHotelList

      }
  },
  methods: {
    ...mapMutations([
      'set_hotelListParams',
      'set_hotelListLoading'
    ]),
    ...mapActions([
      'getHotelList',
        'addRegionFilter'
    ]),

    pageChange(page, pageSize) {
        console.log("page",page);
        console.log("pageSize",pageSize);

      const data = {
        pageNo: page - 1,
          pageSize:pageSize
      };
      this.set_hotelListParams(data)

    },
    jumpToDetails(id){
      this.$router.push({ name: 'hotelDetail', params: { hotelId: id }})
    }
  }
}
</script>
<style scoped lang="less">
  .hotelList {
    text-align: center;
    padding: 50px 0;
      background-color: rgb(245, 248, 255);
    .emptyBox {
      height: 0;
      margin: 10px 10px
    }
    .card-wrapper{
      display: flex;
      justify-content: space-around;
      flex-wrap: wrap;
      flex-grow: 3;
      min-height: 500px
    }
    .card-wrapper .card-item {
      margin: 30px;
      position: relative;
      height: 188px;
    }
      .pagination{
          float:right;
      }

  }
</style>