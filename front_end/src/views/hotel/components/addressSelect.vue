<template>
    <div class="container">
        <a-row  class="row" type="flex">
            <a-col span="2" class="col">
                <a-button class="bt" :class="{isbtActive:btactive}" @click="sort">综合排序</a-button>
            </a-col>
            <a-col span="3" class="col">
                    <a-button @click="sortPrice" class="bt" :class="{ispriceActive:priceactive}">{{price_sort}}</a-button>
            </a-col>
            <a-col span="3" class="col">
                    <a-button @click="sortRate" class="bt" :class="{israteActive:rateactive}" >{{ rateactive?'评分从高到低':'评分'}}</a-button>
            </a-col>
            <a-col span="2">
                <span>地址:</span>
            </a-col>
            <a-col span="3" class="col">
                <a-cascader class="address_select"
                        :options="options"
                        :default-value="address_"
                        expand-trigger="hover"
                        @change="addressChange"

                />
            </a-col>
            <a-col span="2">
                <span>商圈:</span>
            </a-col>
            <a-col span="3">
                <a-select class="district_select"
                          v-model="bizRegion" style="width: 90%;"
                            @change="bizRegionChange">
                    <a-select-option v-for="(item) in bizRegion_select" :key="item">{{item}}</a-select-option>

                </a-select>
            </a-col>

        </a-row>
    </div>
</template>
<script>
    import {mapActions, mapGetters, mapMutations} from 'vuex'
    export default {
        name: "addressSelect",
        data() {
            return {
                price_sort:'价格',
                rate_sort:'评分',
                pricelowToHIgh:null,

                btactive:true,
                priceactive:false,
                rateactive:false,
                address_:['江苏', '南京'],
                bizRegion:'XiDan',
                bizRegion_select:[ 'XiDan','XinJieKou','山西路','江东门','FuZiMiao'],
                options: [
                    {
                        value: '江苏',
                        label: '江苏',
                        children: [
                            {
                                value: '南京',
                                label: '南京',
                            },
                        ],
                    },
                    {
                        value: '浙江',
                        label: '浙江',
                        children: [
                            {
                                value: '杭州',
                                label: '杭州',

                            },
                        ],
                    },
                ],
                bizRegions:[
                    {
                        name:'南京',
                        value:[
                            'XiDan','XinJieKou','山西路','江东门','FuZiMiao'
                        ]
                    },
                    {
                        name:'杭州',
                        value:[
                            'XiCheng','利星名品'
                        ]
                    }
                ]
            };
        },
        computed: {
            ...mapGetters([
                'hotelListLoading'
            ])
        },
        methods: {
            ...mapMutations([
                'set_hotelListParams',
                'set_hotelListLoading',
                'set_addRegion',
                'set_rankAll',
                'set_typeAll'
            ]),

            ...mapActions([
                'getHotelList',
                'addRegionFilter',
                'priceSort',
                'rateSort'
            ]),

            //地址改变时调用
            addressChange(value) {
                if(value.length!==0) {
                    this.set_hotelListLoading(true);
                    this.address_ = value;
                    this.bizRegions.forEach((item) => {
                        if (item.name === value[value.length-1]) {
                            this.bizRegion_select = item.value;
                            this.bizRegion=item.value[0]

                        }
                    });
                    const params={
                        address:this.address_[this.address_.length-1],
                        bizRegion:this.bizRegion
                    };
                    console.log(params);
                    this.set_addRegion(params);
                    this.$options.methods.sort.bind(this)();
                    this.set_rankAll(true);
                    this.set_typeAll(true);
                    this.$emit('changeSelectBox')
                }

            },
            //商圈改变时调用
            bizRegionChange(v){
                this.set_hotelListLoading(true);
                const params={
                    address:this.address_[this.address_.length-1],
                    bizRegion:this.bizRegion
                };
                console.log(params);
                this.set_addRegion(params);
                this.$options.methods.sort.bind(this)();
                this.set_rankAll(true);
                this.set_typeAll(true);
                this.$emit('changeSelectBox')

            },
            //综合排序
            sort(){
                this.btactive=true;
                this.priceactive=false;
                this.rateactive=false;

                this.price_sort='价格';
                this.rate_sort='评分';
                this.addRegionFilter()
            },
            //价格排序
            sortPrice(){
                this.priceactive=true;
                this.rateactive=false;
                this.btactive=false;
                this.pricelowToHIgh=!this.pricelowToHIgh;
                if(this.pricelowToHIgh){
                    this.price_sort='价格从低到高';
                    this.priceSort({priceSortJudge:false})
                }
                else{
                    this.price_sort='价格从高到低';
                    this.priceSort({priceSortJudge:true})
                }

            },
            //评分排序
            sortRate(){
                this.priceactive=false;
                this.price_sort='价格';
                this.pricelowToHIgh=true;
                this.rateactive=!this.rateactive;
                if(this.rateactive){
                    this.rateSort();
                    this.btactive=false
                }
                else if(!this.rateactive&&!this.priceactive){
                    this.$options.methods.sort.bind(this)();
                }
            },

        },
    };
</script>

<style scoped>
    .container{
        width: 100%;
        margin: 0 auto;
        border-top:1px solid #1890ff;

    }
    .row{
        height: 40px;
        line-height: 40px;
    }
    .bt{
        background-color: #f0f2f5;
        border-radius: 0;
        border: 0;
        border-bottom: 1px solid #ffffff;
        border-right: 1px solid #ffffff;
        color:#2c3e50;
        height: 100%;
        width: 100%;
    }
    .bt:hover{
        background-color: #ffffff;
        color: #1890ff;
    }

    .address_select{
        display: inline-block;
        text-align: left;
        width: 100%;

    }

    .district_select{
        display: inline-block;
        width: 100%;
        border-right: 1px solid #ffffff;
    }
</style>