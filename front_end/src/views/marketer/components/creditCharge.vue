<template>
    <a-modal
            :visible="creditChargeVisible"
            title="信用充值"
            cancelText="取消"
            okText="确定"
            @cancel="cancel"
            @ok="handleSubmit">

        <a-form-item label="充值对象" v-bind="formItemLayout">
            <a-input
                    placeholder="请填写充值用户ID"
                    v-model="creditChargeUserID"
            />
        </a-form-item>
        <a-form-item label="充值金额" v-bind="formItemLayout">
            <a-select
                    placeholder="请选择充值金额"
                    v-model="chargeMoney"
                    style="width: 30%;"
            >
                <a-select-option value="50">50  </a-select-option>
                <a-select-option value="100">100 </a-select-option>
                <a-select-option value="200">200 </a-select-option>
                <a-select-option value="500">500 </a-select-option>
                <a-select-option value="1000">1000</a-select-option>
            </a-select>
        </a-form-item>
        <a-form-item label="备注" v-bind="formItemLayout">
            <a-input
                    type="textarea"
                    :rows="4"
                    placeholder="充值相关备注"
                    v-model="relatedInformation"
            />
        </a-form-item>
    </a-modal>

</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    export default {
        name: 'creditCharge',
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
                creditChargeUserID: '',
                chargeMoney: '',
                relatedInformation: '',
            }
        },
        computed: {
            ...mapGetters([
                'creditChargeVisible',
            ]),
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, { name: 'creditCharge' });
        },
        methods: {
            ...mapMutations([
                'set_creditChargeVisible',
            ]),
            ...mapActions([
                'userCreditCharge'
            ]),
            set(data){
                this.creditChargeVisible = data
            },
            cancel() {
                this.set_creditChargeVisible(false)
            },
            changeChargeMoney(v){

            },
            handleSubmit() {
                const data = {
                    creditChargeUserID: this.creditChargeUserID,
                    chargeMoney: this.chargeMoney,
                    relatedInformation: this.relatedInformation,
                };
                console.log(data);
                this.userCreditCharge(data);
                this.set_creditChargeVisible(false)
            },
        }
    }
</script>
