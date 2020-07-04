<template>
    <div class="filter-container">
        <div class="filter-header">
            <span class="filter_text">全部分类<a-icon type="right"/></span>
            <a href="#" class="tag-select" v-show="rankAll">
                <span>酒店星级: ALL</span>
            </a>
            <a href="#" class="tag-select" v-show="typeAll">
                <span>房间类型: ALL</span>
            </a>
            <a href="#" v-for="(item,index) in selectBox" class="tag-select" :key="index">
                <span>{{item.text}}</span>
                <i @click="removeCurrentSelect(index)">&times;</i>
            </a>
            <a-button class="togglebt" @click="togglebox">{{btnTxt ?'收起选项':'更多选项'}}
                <a-icon v-if="btnTxt" type="up"/>
                <a-icon v-else type="down"/>
            </a-button>
        </div>

        <transition name="select-box">
            <div class="box" v-show="boxshow">
                <a-row v-for="(item,index) in filterBox" :key="index" class="row" type="flex">
                    <a-col span="2">{{item.name}}</a-col>
                    <a-col span="2">
                        <a href="#"
                           @click="allChange(item,index)"
                           class="text-filter" :class="activeAllChange(index)">
                            <span>全部</span>
                        </a>
                    </a-col>
                    <a-col span="2" v-for="(v,i) in item.items" :key="i">
                        <a href="#"
                           @click="clickrange(index,v,i)"
                           class="text-filter" :class="{ isActive:v.active}">
                            <span>{{v.text}}</span>
                        </a>
                    </a-col>
                </a-row>
                <a-row class="row" type="flex">
                    <a-col span="2">评分区间:</a-col>
                    <a-col span="6">
                        <a-select class="input-select"
                                  v-model="start_score">
                            <a-select-option value="1">1.0</a-select-option>
                            <a-select-option value="2">2.0</a-select-option>
                            <a-select-option value="3">3.0</a-select-option>
                            <a-select-option value="4">4.0</a-select-option>
                            <a-select-option value="5">5.0</a-select-option>
                        </a-select>
                        <span>---</span>
                        <a-select class="input-select"
                                  v-model="end_score">
                            <a-select-option value="1">1.0</a-select-option>
                            <a-select-option value="2">2.0</a-select-option>
                            <a-select-option value="3">3.0</a-select-option>
                            <a-select-option value="4">4.0</a-select-option>
                            <a-select-option value="5">5.0</a-select-option>
                        </a-select>
                    </a-col>
                    <a-col span="4"></a-col>
                    <a-col span="2">日期:</a-col>
                    <a-col span="9">
                        <a-range-picker
                                class="date_picker"
                                format="YYYY-MM-DD"
                                @change="changeDate"
                                :disabled-date="disabledDate"
                                :placeholder="['入住日期','退房日期']"
                        />
                    </a-col>
                </a-row>
                <a-row class="row" type="flex">
                    <a-col span="2">价格区间:</a-col>
                    <a-col span="6">
                        <a-input class="input-select" v-model="start_price"
                                 placeholder="￥"
                        />
                        <span>---</span>
                        <a-input class="input-select" v-model="end_price"
                                 placeholder="￥"
                        />
                    </a-col>
                    <a-col span="4"></a-col>
                    <a-col span="2">预订状态:</a-col>
                    <a-col span="3">
                        <a-select v-model="orderState" class="state_select">
                            <a-select-option value="0">全部</a-select-option>
                            <a-select-option value="1">预订过</a-select-option>

                        </a-select>
                    </a-col>
                    <a-col span="7"></a-col>

                </a-row>
                <a-row class="row" type="flex">
                    <a-col span="24">
                        <a-button type="primary" style="margin-right: 5px;" @click="determine">确定</a-button>
                        <a-button type="normal" style="margin-left: 5px;" @click="cancel">取消</a-button>
                    </a-col>
                </a-row>
            </div>
        </transition>
        <address-select @changeSelectBox="changeSelectBox"></address-select>
    </div>
</template>

<script>
    import addressSelect from "./addressSelect";
    import {mapActions, mapGetters, mapMutations} from 'vuex'
    const moment = require('moment');
    export default {
        name: "filterBox",
        components: {
            addressSelect
        },
        data() {
            return {
                boxshow: false,
                btnTxt: false,
                selectBox: [],
                text_scores: '评分区间:',
                text_numbers: '数量',
                text_prices: '价格区间',
                text_date: '日期',
                date: null,
                nums: null,
                start_price: null,
                end_price: null,
                start_score: null,
                end_score: null,
                start_date: '',
                end_date: '',
                select_times:0,
                orderState:'全部',
                filterBox: [
                    {
                        name: '酒店星级:',
                        items: [
                            // {value: 'rank0', text: '全部', active: true},
                            {value: 'rank1', text: '五星级',text_en:'Five' ,active: false},
                            {value: 'rank2', text: '四星级',text_en:'Four', active: false},
                            {value: 'rank3', text: '三星级',text_en:'Three', active: false},
                            {value: 'rank4', text: '经济型',text_en:'Economy', active: false}
                        ]
                    },
                    {
                        name: '房间类型:',
                        items: [
                            // {value: 'type0', text: '全部', active: true},
                            {value: 'type1', text: '单人房',text_en:'SingleBed' ,active: false},
                            {value: 'type2', text: '双床房',text_en:'DoubleBed' , active: false},
                            {value: 'type3', text: '大床房',text_en:'BigBed' , active: false},
                            {value: 'type4', text: '家庭房', text_en:'Family' ,active: false},
                            {value: 'type5', text: '豪华套房', text_en:'Luxury' ,active: false},
                            {value: 'type6', text: '主题套房', text_en:'Theme' ,active: false}

                        ]
                    }
                ]
            }
        },
        computed: {
            ...mapGetters([
                'hotelListLoading',
                'rankAll',
                'typeAll',
                'hotelList',
                'userOrderList',
            ]),
            activeAllChange:function () {
                return (index)=>{
                    return index===0?{isActive:this.rankAll}:{isActive:this.typeAll}
                }
            }
        },
        async mounted() {
            console.log("hotel",this.hotel);
            await this.getUserOrders();
        },
        methods: {
            ...mapMutations([
                'set_hotelListParams',
                'set_hotelListLoading',
                'set_rankAll',
                'set_typeAll',
                'set_hotelList'
            ]),

            ...mapActions([
                'getHotelList',
                'filterHotelList',
                'addRegionFilter',
                'getUserOrders'
            ]),
            //禁止选择今天以前的日期
            disabledDate(current) {
                return current&&current < moment().startOf('day');
            },
            //点击更多选项时触发
            togglebox: function () {
                this.boxshow = !this.boxshow;

                this.select_times=0;
                if(!this.boxshow)
                    this.$options.methods.determine.bind(this)();
                else
                    this.btnTxt = !this.btnTxt;

            },
            //检查Select_Box中的rank和type
            checkEmptyRankandType(){
                let rankflag=false;
                let typeflag=false;
                this.selectBox.forEach((v,i)=>{
                    if(v.value.search('rank') !== -1){
                        rankflag=true
                    }
                    if(v.value.search('type') !== -1){
                        typeflag=true
                    }
                });
                if(!rankflag) {
                    this.set_rankAll(true)
                }
                else
                    this.set_rankAll(false);
                if(!typeflag){
                    this.set_typeAll(true)
                }
                else
                    this.set_typeAll(false)

            },
            // 点击全部时调用
            allChange(item,index){
                switch (index) {
                    case 0:
                        this.set_rankAll(true);
                        break;
                    case 1:
                        this.set_typeAll(true);
                        break;
                }
                item.items.forEach((v, i) => {
                    v.active = false
                });

                this.selectBox=this.selectBox.filter((v, i) => {
                    return v.active===true
                });

            },
            //星级和房型按钮点击时调用
            clickrange(parentIndex, el, childIndex) {
                var item = this.filterBox[parentIndex].items;
                //选中除全部外的数据
                item.filter((v, i) => {
                    if (i === childIndex) {
                        if (v.active === true) {
                            v.active = !v.active;
                            this.selectBox.filter((childEl, childI) => {
                                if (childEl.active === false) {
                                    this.selectBox.splice(childI, 1);
                                }
                            });
                        } else if (this.selectBox.length < 8) {
                            v.active = !v.active; // 选中和反选
                            this.selectBox.push(v); // 选中的数组
                        } else {
                            alert("标签最多只能选8个")
                        }
                    }

                });
                this.$options.methods.checkEmptyRankandType.bind(this)();
            },
            //移除标签时调用
            removeCurrentSelect(index) {
                this.filterBox.filter((el) => {
                    el.items.filter((data) => {
                        if (data.text === this.selectBox[index].text) {
                            data.active = false;
                        }
                    });
                });
                this.selectBox.splice(index, 1);
                this.$options.methods.checkEmptyRankandType.bind(this)();
                if(!this.boxshow){
                const data = {
                    ranks: this.getranks(),//酒店星级,数组,default:[]
                    types: this.gettypes(),//房间类型,数组,default:[]
                    //评分区间
                    startRate: this.start_score?Number(this.start_score):0.0,//default:0.0
                    endRate: this.end_score?Number(this.end_score):5.0,//default:5.0
                    //价格区间
                    startPrice: this.start_price?Number(this.start_price):0.0,//default:0.0
                    endPrice: this.end_price?Number(this.end_price):1000000,//default:0.0
                    //日期
                    startDate:this.start_date,//default:''
                    endDate: this.end_date//default:''
                };
                // console.log("data:",data)
                this.filterHotelList(data)
                }
            },
            //日期格式化
            changeDate(v) {
                this.start_date=moment(v[0]).format('YYYY-MM-DD');
                this.end_date=moment(v[1]).format('YYYY-MM-DD')
            },
            //点击确定时调用
            determine() {
                this.btnTxt = !this.btnTxt;
                this.boxshow = false;
                this.select_times=this.selectBox.length;
                this.set_hotelListLoading(true);
                const data = {
                    ranks: this.getranks(),//酒店星级,数组,default:[]
                    types: this.gettypes(),//房间类型,数组,default:[]
                    //评分区间
                    startRate: this.start_score?Number(this.start_score):0.0,//default:0.0
                    endRate: this.end_score?Number(this.end_score):5.0,//default:5.0
                    //价格区间
                    startPrice: this.start_price?Number(this.start_price):0.0,//default:0.0
                    endPrice: this.end_price?Number(this.end_price):1000000,//default:0.0
                    //日期
                    startDate:this.start_date,//default:''
                    endDate: this.end_date//default:''
                };
                    this.filterHotelList(data);
                    setTimeout(()=>{
                        if(this.orderState===1){
                           let list=[];
                            list=this.hotelList.filter((item)=>{
                                for(let i=0;i<this.userOrderList.length;i++){
                                    if(this.userOrderList[i].hotelId===item.id)
                                        return true
                                }
                                return  false
                            });
                            this.set_hotelList(list);
                            console.log("hotelList",this.hotelList)
                        }
                    },800)
            },
            //点击取消时调用
            cancel() {
                this.btnTxt = !this.btnTxt;
                this.boxshow = false;
                this.selectBox=this.selectBox.filter((item)=>{
                    item.active=false;
                    return item.active===true
                });
                this.set_rankAll(true);
                this.set_typeAll(true);
            },
            //向后端传递参数时所需的ranks
            getranks() {
                let ranks = [];
                this.selectBox.forEach((item, index) => {
                    if (item.value.search('rank') !== -1) {
                        ranks.push(item.text_en)
                    }
                });
                if(ranks.length===0){
                    this.filterBox[0].items.forEach((item,index)=>{
                            ranks.push(item.text_en)
                    })
                }
                return ranks
            },
            //向后端传递参数时所需的types
            gettypes() {
                let types = [];
                this.selectBox.forEach((item, index) => {
                    if (item.value.search('type') !== -1) {
                        types.push(item.text)
                    }
                });
                if(types.length===0){
                    this.filterBox[1].items.forEach((item,index)=>{
                            types.push(item.text)
                    })
                }
                return types
            },
            //addressSelect中地址和商圈改变时调用这个函数
            changeSelectBox(){
                this.selectBox.forEach((item)=>{
                    item.active=false
                });
                this.selectBox=[];
            }

        }

    }


</script>

<style scoped>
    .filter-container {
        width: 100%;
        margin: 0 auto;
        /*border: 1px solid #e8f4fd;*/
        padding: 25px 15px;
    }

    .filter-header {
        display: inline-block;
        text-align: left;
        height: 40px;
        width: 100%;
    }

    .filter_text {
        float: left;
        margin-top: 8px;
        line-height: 20px;
        margin-left: 10px;
    }
    .box {
        height: 200px;
        overflow: hidden;
        background-color: #f0f2f5;
    }

    .togglebt {
        float: right;
        text-align: center;
        vertical-align: center;
        cursor: pointer;
    }

    .text-filter {
        text-decoration: none;
        display: inline-block;
        /*width:60px;*/
        color: #404040;
    }

    .row {
        height: 40px;
        line-height: 40px;
        border-bottom: 1px solid white;
    }

    .text-filter span {
        display: block;
        isplay: inline-block;
        text-align: center;
        width: 60px;
        line-height: 30px;
    }

    .text-filter :hover {
        animation: myfirst .5s;
        -moz-animation: myfirst 1s; /* Firefox */
        -webkit-animation: myfirst 1s; /* Safari and Chrome */
        -o-animation: myfirst 1s; /* Opera */
        animation-fill-mode: forwards;
    }

    .tag-select {
        text-decoration: none;
        height: 20px;
        line-height: 20px;
        border: 1px solid #1890ff;
        border-radius: 2px;
        color: #1890ff;
        text-align: center;
        font-size: 14px;
        padding: 0 5px;
        margin-top: 8px;
        margin-left: 15px;
        float: left;
    }

    .tag-select i {
        display: inline-block;
        height: 100%;
        font-size: 15px;
        padding: 0 5px;
    }

    .input-select {
        display: inline-block;
        width: 43%;
    }
    .state_select{
        display: inline-block;
        width: 90%;
    }

    .nums_select {
        display: inline-block;
        width: 90%;
    }

    .date_picker {
        width: 96.7%;
    }

    @keyframes myfirst {
        from {
            opacity: 1.0;
        }
        to {
            opacity: 0.5;
        }
    }

    @keyframes mysecond {
        from {
            color: #404040;;
        }
        to {
            color: #ffffff;
        }
    }
</style>