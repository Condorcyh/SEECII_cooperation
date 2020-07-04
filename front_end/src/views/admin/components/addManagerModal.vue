<template>
    <a-modal
        :visible="addManagerModalVisible"
        title="添加用户"
        cancelText="取消"
        okText="确定"
        @cancel="cancel"
        @ok="handleSubmit"
    >
    <a-Form :form="form">
        <a-form-item v-bind="formItemLayout" label="用户类型">
            <a-select
                v-decorator="[
                    'type',
                    {rules:[{required: true,message:'请选择用户类型'}]}
                ]">
                <a-select-option value="2">酒店工作人员</a-select-option>
                <a-select-option value="4">网站营销人员</a-select-option>
            </a-select>
        </a-form-item>
        <a-form-item v-bind="formItemLayout" label="所属酒店ID" v-show="this.form.getFieldValue('type')=='2'">
            <a-input
                    v-decorator="[
                        'hotelId',
                        { rules: [{required: this.form.getFieldValue('type')=='2', message: '请输入hotelId', }] }
                    ]"
            />
        </a-form-item >
        <a-form-item v-bind="formItemLayout" label="用户邮箱">
                <a-input
                    v-decorator="[
                        'email',
                        { rules: [{required: true, message: '请输入用户邮箱', }] }
                    ]"
                />
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="密码">
                <a-input
                    v-decorator="[
                        'password',
                        { rules: [{required: true, message: '请输入密码', }] }
                    ]"
                />
            </a-form-item >
    </a-Form>
    </a-modal>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
export default {
    name: 'addManagerModal',
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
            userType:null
        }
    },
    computed: {
        ...mapGetters([
            'addManagerModalVisible',
            'managerList',
        ])
    },
    beforeCreate() {
        this.form = this.$form.createForm(this, { name: 'addManagerModal' });
    },
    mounted() {

    },
    methods: {
        ...mapMutations([
            'set_addManagerModalVisible',
            'set_addManagerParams',
        ]),
        ...mapActions([
            'getManagerList',
            'addManager',
        ]),
        cancel() {
            this.set_addManagerModalVisible(false);
            this.form.resetFields()
        },
        handleSubmit(e) {
            e.preventDefault();
            this.form.validateFieldsAndScroll((err, values) => {
                if (!err) {
                    const data = {
                        userType: this.form.getFieldValue('type'),
                        email: this.form.getFieldValue('email'),
                        password: this.form.getFieldValue('password'),
                        hotelId:this.form.getFieldValue('hotelId')?Number(this.form.getFieldValue('hotelId')):''
                    };
                    console.log(data);
                    this.set_addManagerParams(data);

                    this.addManager();
                    this.form.resetFields()
                }
            });
        },
    }
}
</script>