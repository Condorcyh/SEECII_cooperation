<template>
    <a-modal
            :visible="modifyRoomInfoVisible"
            title="修改客房信息"
            cancelText="取消"
            okText="确定"
            @cancel="cancel"
            @ok="handleSubmit"
    >
        <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">
            <a-form-item label="房型" v-bind="formItemLayout">
                <span>{{roomInfo.roomType}}</span>
            </a-form-item>
            <a-form-item label="剩余房间数量" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写剩余房间数量"
                        v-decorator="['curNum', { rules: [{ required: true, message: '请输入剩余房间数量' }] }]"
                />
            </a-form-item>
            <a-form-item label="总房间数量" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写总房间数量"
                        v-decorator="['total', { rules: [{ required: true, message: '请输入总房间数量' }] }]"
                />
            </a-form-item>
            <a-form-item label="床型" v-bind="formItemLayout">
                <a-select
                        v-decorator="[
                    'bedType',
                    { rules: [{ required: true, message: '请选择床型' }] }]"
                >
                    <a-select-option value="BigBed">标准大床</a-select-option>
                    <a-select-option value="SingleBed">单人房</a-select-option>
                    <a-select-option value="DoubleBed">双人床</a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item label="最多入住人数" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写最多入住人数"
                        v-decorator="['peopleNum', { rules: [{ required: true, message: '请输入最多入住人数' }] }]"
                />
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="有无早餐">
                <a-radio-group
                        v-decorator="[
                        'breakfast',
                        { rules: [{required: true, message: '请选择有无早餐', }] }
                    ]"
                >
                    <a-radio :value="1">有</a-radio>
                    <a-radio :value="0">无</a-radio>
                </a-radio-group>
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="有无WiFi">
                <a-radio-group
                        v-decorator="[
                        'wifi',
                        { rules: [{required: true, message: '请选择有无WiFi', }] }
                    ]"
                >
                    <a-radio :value="1">有</a-radio>
                    <a-radio :value="0">无</a-radio>
                </a-radio-group>
            </a-form-item>
            <a-form-item label="价格" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写价格"
                        v-decorator="['price', { rules: [{ required: true, message: '请输入价格' }] }]"
                />
            </a-form-item>
        </a-form>
    </a-modal>
</template>

<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    export default {
        name: 'modifyRoomInfo',
        data() {
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
                roomtypes:{

                }
            }
        },
        props:{
            roomInfo:{
                type:Object,
                default:function () {
                    return {}
                }
            }
        },
        computed: {
            ...mapGetters([
                'addRoomModalVisible',
                'activeHotelId',
                'hotelInfo',
                'modifyRoomInfoVisible'
            ])
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, { name: 'modifyRoomModal' });

        },
        mounted() {


        },
        methods: {
            ...mapMutations([
                'set_addRoomModalVisible',
                'set_addRoomParams',
                'set_modifyRoomInfoVisible',
            ]),
            ...mapActions([
                'addRoom',
                'modifyRoomInfo'
            ]),
            cancel() {
                this.set_modifyRoomInfoVisible(false)
            },
            handleSubmit(e) {
                e.preventDefault();
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        let roomtype = '';
                       if(this.roomInfo.roomType ==='家庭房'){
                           roomtype='Family'
                       }
                       else if(this.roomInfo.roomType ==='双床房'){
                           roomtype='DoubleBed'
                        }
                       else if(this.roomInfo.roomType ==='大床房'){
                           roomtype='BigBed'
                        }
                       else if(this.roomInfo.roomType ==='单人房'){
                           roomtype='SingleBed'
                       }
                       else if(this.roomInfo.roomType ==='主题套房'){
                           roomtype='Theme'
                       }
                       else if(this.roomInfo.roomType ==='豪华套房'){
                           roomtype='Luxury'
                       }
                        const data = {
                            id:this.roomInfo.id,
                            roomType: roomtype,
                            bedType:this.form.getFieldValue('bedType'),
                            price: Number(this.form.getFieldValue('price')),
                            total: Number(this.form.getFieldValue('total')),
                            curNum: Number(this.form.getFieldValue('curNum')),
                            peopleNum:Number(this.form.getFieldValue('peopleNum')),
                            breakfast:Number(this.form.getFieldValue('breakfast')),
                            wifi:Number(this.form.getFieldValue('wifi')),
                        };
                        this.modifyRoomInfo(data);
                        this.set_modifyRoomInfoVisible(false)
                    }
                });
            },
        },
        watch:{
            //点击修改时对form赋予初始值
            roomInfo(val){
                console.log("val",val);
                setTimeout(()=>{
                    this.form.setFieldsValue({
                        roomType: val.roomType,
                        bedType:val.bedType,
                        price: val.price,
                        total:val.total,
                        curNum:val.curNum,
                        peopleNum:val.peopleNum,
                        breakfast:val.breakfast,
                        wifi:val.wifi,
                    })
                },0)
            }
        }
    }
</script>

<style scoped>

</style>