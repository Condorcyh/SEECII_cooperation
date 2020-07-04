<template>
    <a-modal
            :visible="iconChooseVisible"
            title="头像选择"
            cancelText="取消"
            okText="确定"
            @cancel="cancel"
            @ok="handleSubmit">
        <div class="avatarList">
            <div v-for="(item,index) in icons" :key="index" class="avatar" :class="item.active?'active':''" @click="imgChoose(index)">
            <a-avatar :src="item.url" alt="./defaultAvatar.png" :size="64"></a-avatar>
                <span style="display: block;text-align: center">{{index+1}}</span>
            </div>
        </div>
    </a-modal>
</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    export default {
        name: 'iconChoose',
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
                iconChoose:null,
                icons:[
                    {
                        url:'https://i.loli.net/2020/06/25/wCg9PHeFyqckYUL.png',
                        active:false
                    },
                    {
                        url:'https://i.loli.net/2020/06/25/RaIXBw54EYDCPUk.png',
                        active:false
                    },
                    {
                        url:'https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png',
                        active:false
                    },
                    {
                        url:'https://i.loli.net/2020/06/25/IAt8nojc9T6fEZh.jpg',
                        active:false
                    },
                    {
                        url:'https://i.loli.net/2020/06/25/pn7ERSibfMukTWV.jpg',
                        active:false
                    },


                ]
            }
        },
        computed: {
            ...mapGetters([
                'iconChooseVisible',
                'userInfo'
            ]),
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, { name: 'iconChoose' });
        },
        methods: {
            ...mapMutations([
                'set_iconChooseVisible',
                'set_iconChoose',
            ]),
            ...mapActions([
                'iconChoice'
            ]),
            //点击头像时active设为true,其他设为false
            imgChoose(index){
                this.icons.forEach((item)=>{
                    item.active=false
                })
                this.icons[index].active=true
            },
            //取消
            cancel() {
                this.set_iconChooseVisible(false)
            },
            //确定
            handleSubmit() {
                let url=''
                this.icons.forEach((item)=>{
                    if(item.active){
                        url=item.url
                    }
                })
                if(url!=''){
                    let data={
                        userId:this.userInfo.id,
                        iconChoose: url
                    }
                    this.iconChoice(data)
                    this.set_iconChoose(url)
                    this.set_iconChooseVisible(false)
                }

            },
        }
    }
</script>
<style scoped lang="less">
    .avatarList{
        margin-top: 10px;
        display: flex;
        justify-content: space-around;
    }
    .avatar{
        padding: 10px 10px;
        cursor: pointer;
    }
    .active{
        border-radius: 3px;
        border: 1px #1890ff solid;
    }

</style>