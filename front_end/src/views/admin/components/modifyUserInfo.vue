<template>
    <a-modal
            :visible="modifyUserInfoVisible"
            title="修改用户信息"
            cancelText="取消"
            okText="确定"
            @cancel="cancel"
            @ok="handleSubmit"
    >
        <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">
            <a-form-item label="用户名">
                <a-input
                        placeholder="请填写用户名"
                        v-decorator="['userName', { rules: [{ required: true, message: '请输入用户名' }] }]"
                />
            </a-form-item>
            <a-form-item label="用户ID">
                <span>{{ userInfo.id}}</span>
            </a-form-item>
            <a-form-item label="邮箱">
                <span>{{ userInfo.email }}</span>
            </a-form-item>

            <a-form-item label="手机号">
                <a-input
                        placeholder="请填写手机号"
                        v-decorator="['phoneNumber', { rules: [{ required: true, message: '请输入手机号' }] }]"
                />

            </a-form-item>
            <a-form-item label="生日" >
                <a-input
                        placeholder="格式:1998-07-14(选填)"
                        v-decorator="['birthday', { rules: [{ required: false, message: '请输入正确的生日格式',pattern:'((((19|20)\\d{2})-(0?(1|[3-9])|1[012])-(0?[1-9]|[12]\\d|30))|(((19|20)\\d{2})-(0?[13578]|1[02])-31)|(((19|20)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|((((19|20)([13579][26]|[2468][048]|0[48]))|(2000))-0?2-29))$' }] }]"
                        v-if="modify"
                />
                <span v-else>{{ userInfo.birthday }}</span>
            </a-form-item>
            <a-form-item label="信用值">
                <span>{{ userInfo.credit }}</span>
            </a-form-item>
            <a-form-item label="VIP等级" >
                <span>{{ userInfo.vipLevel }}</span>
            </a-form-item>
            <a-form-item label="企业名称" >
                <a-input
                        placeholder="请填写企业名称(选填)"
                        v-decorator="['enterprise', { rules: [{ required: false, message: '请输入企业名称' }] }]"
                />
            </a-form-item>
            <a-form-item label="密码">
                <a-input
                        placeholder="请输入新密码"
                        v-decorator="['password', { rules: [{ required: true, message: '请输入新密码' }] }]"
                />
            </a-form-item>

        </a-form>
    </a-modal>
</template>

<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    export default {
        name: 'modifyUserInfo',
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

            }
        },
        props:{
            userInfo:{
                type:Object,
                default:function () {
                    return {}
                }
            }
        },
        computed: {
            ...mapGetters([
                'modifyUserInfoVisible'
            ])
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, { name: 'modifyUserInfo' });
        },
        mounted() {

        },
        methods: {
            ...mapMutations([

                'set_modifyUserInfoVisible',
            ]),
            ...mapActions([
                'updateManagerInfo'
            ]),
            cancel() {
                this.set_modifyUserInfoVisible(false)
            },
            handleSubmit(e) {
                e.preventDefault();
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        const data = {
                            id:this.userInfo.id,
                            userName: this.form.getFieldValue('userName'),
                            phoneNumber: this.form.getFieldValue('phoneNumber'),
                            password: this.form.getFieldValue('password'),
                            birthday: this.form.getFieldValue('birthday')?this.form.getFieldValue('birthday'):null,
                            enterprise: this.form.getFieldValue('enterprise')?this.form.getFieldValue('enterprise'):null
                        };
                        this.updateManagerInfo(data);
                        this.set_modifyUserInfoVisible(false)
                    }
                });
            },
        },
        watch:{
            //点击修改时对form赋予初始值
            userInfo(val){
                console.log("val",val);
                setTimeout(()=>{
                    this.form.setFieldsValue({
                        'userName':val.userName,
                        'phoneNumber': val.phoneNumber,
                        'birthday': val.birthday,
                        'enterprise': val.enterprise,
                        'password': val.password
                    })
                },0)
            }
        }
    }
</script>

<style scoped>

</style>