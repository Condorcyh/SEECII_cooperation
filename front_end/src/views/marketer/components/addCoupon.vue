<template>
    <a-modal
            :visible="addCouponVisible"
            title="添加优惠策略"
            cancelText="取消"
            okText="确定"
            @cancel="cancel"
            @ok="handleSubmit"
    >
        <!-- 这里是添加策略模态框区域，请编写表单 -->
        <a-form   :form="form" style="margin-top: 30px" v-bind="formItemLayout">
            <a-form-item label="优惠券类型" v-bind="formItemLayout">
                <a-select @change="changeType"
                        v-decorator="[
                    'type',
                    { rules: [{ required: true, message: '请选择优惠券类型' }] }]"
                >
                    <a-select-option value="3">满减优惠</a-select-option>
                     <a-select-option value="4">限时优惠</a-select-option>
                    <a-select-option value="6">VIP优惠</a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item label="券名" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写券名"
                        v-decorator="['name', { rules: [{ required: true, message: '请填写券名' }] }]"
                />
            </a-form-item>
            <a-form-item label="优惠简介" v-bind="formItemLayout">
                <a-textarea
                        placeholder="请填写优惠简介"
                        :rows="4"
                        v-decorator="['description', { rules: [{ required: true, message: '请填写优惠简介' }] }]"
                />
            </a-form-item>
            <a-form-item label="达标间数" v-bind="formItemLayout" v-show="couponType ==='2'">
                <a-input
                        placeholder="请填写达标间数"
                        v-decorator="['targetRoom', { rules: [{ required: false, message: '请填写达标间数' }] }]"
                />
            </a-form-item>
            <a-form-item label="VIP最低等级" v-bind="formItemLayout" v-show="couponType ==='6'">
                <a-select @change="changeVIP"
                          v-decorator="[
                    'targetVip',
                    { rules: [{ required: couponType ==='6', message: '请选择VIP最低等级' }] }]"
                >
                    <a-select-option value="1">1</a-select-option>
                    <a-select-option value="2">2</a-select-option>
                    <a-select-option value="3">3</a-select-option>
                    <a-select-option value="4">4</a-select-option>、
                </a-select>
            </a-form-item>
            <a-form-item label="达标金额" v-bind="formItemLayout"  v-show="couponType ==='3'">
                <a-input
                        placeholder="请填写达标金额"
                        v-decorator="['targetMoney', { rules: [{ required: false, message: '请填写达标金额' }] }]"
                />
            </a-form-item>
            <a-form-item label="折扣类型" v-bind="formItemLayout" >
                <a-select @change="changeDiscountType"
                          v-decorator="[
                    'discountType',
                    { rules: [{ required: true, message: '请选择折扣类型' }] }]"
                >
                    <a-select-option value="1">折扣金额</a-select-option>
                    <a-select-option value="2">折扣</a-select-option>
                    、
                </a-select>
            </a-form-item>
            <a-form-item label="折扣金额" v-bind="formItemLayout" v-show="discountType ==='1'">
                <a-input
                        placeholder="请填写折扣金额"
                        v-decorator="['discountMoney', { rules: [{ required: discountType ==='1', message: '请填写折扣金额' }] }]"
                />
            </a-form-item>
            <a-form-item label="折扣" v-bind="formItemLayout" v-show="discountType ==='2'">
                <a-input
                        placeholder="请填写折扣(0~1之间的小数)"
                        v-decorator="['discount', { rules: [{ required: discountType ==='2', message: '请填写折扣' }] }]"
                />
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="生效时间" v-show="couponType ==='4'">
                <a-range-picker
                        format="YYYY-MM-DD"
                        @change="changeDate"
                        :disabled-date="disabledDate"
                        v-decorator="[
                        'date',
                        {
                            rules: [{ required: false, message: '生效时间' }]
                        }
                    ]"
                        :placeholder="['生效日期','失效日期']"
                />
            </a-form-item>
        </a-form>

    </a-modal>
</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    const moment = require('moment');
    export default {
        name: 'addCouponModal',
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
                couponType:'',
                discountType:'1',

            }
        },
        computed: {
            ...mapGetters([
                'addCouponVisible',
                'websiteHotelId'
            ]),
            getType(){
               console.log("coupontype");
                return this.couponType
            }
        },
        beforeCreate() {
            // 表单名默认为“form”
            this.form=this.$form.createForm(this, { name: 'addCouponModal' })
        },
        mounted() {

        },
        methods: {
            ...mapMutations([
                'set_addCouponVisible'
            ]),
            ...mapActions([
                // addHotelCoupon：添加酒店策略接口
                'addWebsiteTargetRoomCoupon',
                'addWebsiteTargetMoneyCoupon',
                'addWebsiteTimeCoupon',
                'addWebsiteCoopEnterpriseCoupon',
                'addWebsiteVIPCoupon',
                'addWebsiteBirthdayCoupon',
            ]),
            disabledDate(current) {
                // Can not select days before today and today
                return current && current < moment().startOf('day');
            },
            cancel() {
                this.set_addCouponVisible(false);
                this.form.resetFields();
                this.couponType='';
                this.discountType='1'

            },
            changeType(v){
                this.couponType=v

            },
            changeDiscountType(v){
                this.discountType=v;
                console.log("discountType",v)
            },
            changeVIP(v){

            },
            changeDate(v) {

            },
            handleSubmit(e) {
                e.preventDefault();
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        const data = {
                            // 这里添加接口参数
                            type: this.form.getFieldValue('type'),
                            name: (this.form.getFieldValue('name')),
                            description: (this.form.getFieldValue('description')),
                            targetRoom: this.form.getFieldValue('targetRoom') ? Number(this.form.getFieldValue('targetRoom')) : 0,
                            targetMoney: this.form.getFieldValue('targetMoney') ? Number(this.form.getFieldValue('targetMoney')) : 0,
                            discountMoney: this.form.getFieldValue('discountMoney')?Number(this.form.getFieldValue('discountMoney')):-1,
                            discount:this.form.getFieldValue('discount')?Number(this.form.getFieldValue('discount')):-1,
                            startTime: (this.form.getFieldValue('date')) ? moment(this.form.getFieldValue('date')[0]).format('YYYY-MM-DD') : '',
                            targetVip:this.form.getFieldValue('targetVip')?Number(this.form.getFieldValue('targetVip')):-1,
                            endTime: (this.form.getFieldValue('date')) ? moment(this.form.getFieldValue('date')[1]).format('YYYY-MM-DD') : '',
                            hotelId: Number(this.websiteHotelId),
                            status: 1,
                            coopEnterprise:this.form.getFieldValue('coopEnterprise')
                        };
                        let type=Number(this.form.getFieldValue('type'));
                        switch(type){

                            case 3:
                                this.addWebsiteTargetMoneyCoupon(data);
                                break;
                            case 4:
                                this.addWebsiteTimeCoupon(data);
                                break;
                            case 6:
                                this.addWebsiteVIPCoupon(data);
                                break;
                        }
                        this.form.resetFields()
                    }
                });
            },
        }
    }
</script>