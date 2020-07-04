<template>
    <div class="header">

        <div id="parent">
            <!-- iconChoose为目标地址，parentHandle为传递的函数 -->
            <iconChoose></iconChoose>
        </div>

        <div class="label">
            <img src="@/assets/logo2.png" class="logo" alt="logo" @click="jumpToHome">
            <span class="title">GreenTrip</span>
            <span class="title title_2">-您的外出旅行伙伴</span>


        </div>
        <a-menu v-model="current" mode="horizontal" theme="light">
            <a-menu-item key="1" @click="selectMenu">
                <router-link to="/hotel/hotelList">
                    <a-icon type="home" />首页
                </router-link>
            </a-menu-item>
            <a-menu-item key="2" @click="jumpToUserInfo" v-show="userInfo.userType=='Client'">
                <a-icon type="user" />个人中心
            </a-menu-item>
            <a-menu-item key="3" @click="selectMenu" v-show="userInfo.userType=='HotelManager'">
                <router-link :to="{ name: 'manageHotel'}">
                     <a-icon type="switcher" />订单管理
                </router-link>
            </a-menu-item>
            <a-menu-item key="4" @click="selectMenu" v-show="userInfo.userType=='Admin'">
                <router-link :to="{ name: 'manageUser'}">
                     <a-icon type="sketch" />网站管理
                </router-link>
            </a-menu-item>
            <a-menu-item key="5" @click="selectMenu" v-show="userInfo.userType=='Marketer'">
                <!-- 图标:dropbox/sketch -->
                <router-link :to="{ name: 'manageWebsite'}">
                    <a-icon type="dropbox" />网站营销
                </router-link>
            </a-menu-item>
        </a-menu>


        <div class="logout">
            <div  v-show="vipLevel==1">
                <img class="image" src="https://i.loli.net/2020/06/30/NHzM3e7wl5tUcJn.png">
            </div>
            <div  v-show="vipLevel==2">
                <img class="image" src="https://i.loli.net/2020/06/30/SJkP3I8sruzi5DQ.png">
            </div>
            <div  v-show="vipLevel==3">
                <img class="image" src="https://i.loli.net/2020/06/30/QwlrNcJmAzM4WnF.png">
            </div>
            <div  v-show="vipLevel==4">
                <img class="image" src="https://i.loli.net/2020/06/30/nULR3oJlauSKYqB.png">
            </div>
            <a-dropdown placement="bottomCenter">
                <div class="user">
                    <a-avatar :src="userInfo.avatar"></a-avatar>
                    <span style="font-size: 14px">{{ userInfo.userName }}</span>
                    <a-icon style="margin-left: 3px; font-size: 16px" type="down"></a-icon>
                </div>
                <a-menu slot="overlay">
                <a-menu-item @click="jumpToUserInfo()">
                    <a-icon type="profile"></a-icon>
                    我的信息
                </a-menu-item>
                <a-menu-item @click="iconChoice()">
                   <a-icon type="book"></a-icon>
                   头像选择
                </a-menu-item>
                <a-menu-item @click="quit()">
                    <a-icon type="poweroff"></a-icon>
                    退出登录
                </a-menu-item>
                </a-menu>
            </a-dropdown>
        </div>
        <IconChoose></IconChoose>

    </div>

</template>
<script>
import { mapGetters, mapActions, mapMutations } from 'vuex'
import IconChoose from "./iconChoose";

export default {
    name: 'Header',
    components: {IconChoose},
    data() {
        return {
            current: ['1'],
            iconUrl: "",
            rank:this.vipLevel
        }
    },
    computed: {
        ...mapGetters([
            'userId',
            'userInfo',
            'iconChooseVisible',
            'iconChoose',
            'creditChargeVisible',
            'vipLevel',

        ])
    },
  mounted() {
        this.getUserInfo();
        if (this.$route.name === 'hotelList') {
            this.current = ['1']
        }
        else if(this.$route.name === 'hotelDetail') {
            this.current = ['0']
        }
        else if(this.$route.name === 'userInfo') {
            this.current = ['2']
        }else if(this.$route.name === 'manageHotel') {
            this.current = ['3']
        }
        else if(this.$route.name === 'manageHotel') {
            this.current = ['4']

        }else if(this.$route.name === 'manageWebsite'){
            this.current = ['5']
        }
        this.updatevipLevel();
        console.log("vipLevel",this.vipLevel)
    },
    methods: {
        ...mapMutations([
            'set_iconChooseVisible',
            'set_creditChargeVisible',
        ]),
        ...mapActions([
            'logout',
            'updatevipLevel',
            'getUserInfo'
        ]),
        selectMenu(v){
        },
        async quit() {
            await this.$store.dispatch('logout');
            this.$router.push(`/login?redirect=${this.$route.fullPath}`)
        },
        //跳转到用户信息界面
        jumpToUserInfo() {
            console.log("userInfo",this.userInfo);
            this.$router.push({ name: 'userInfo', params: { userId: this.userId } })

        },
        //跳转到主页
        jumpToHome() {
            this.$router.push({path:'/'})
        },
        //点击头像选择时触发
        iconChoice(){
            this.set_iconChooseVisible(true)
        },


    }
}
</script>
<style scoped lang="less">
    @font-face {
        font-family: "Ink Free";
    }
    .header {
        display: flex;
        line-height: 44px;
        height: 44px;
        align-items: center;
        justify-content: space-between;
        min-width: 800px;
        .label{
            height: 44px;
            line-height: 44px;
            vertical-align: middle;
            min-width: 400px;

            .logo {
            height: 44px;
            vertical-align: top;
            margin-right: 16px;
            border-style: none;
            cursor: pointer;
          }

          .title {
            font-size: 38px;
            color: rgba(0, 0, 0, .85);
            font-family: Georgia;
            font-weight: 500;
            position: relative;
            top: 2px;
              font-style: oblique;
          }
            .title_2{
                font-weight: 200;
                font-size: 15px;
            }

          .search {
              width: 300px;
              margin-left: 30px
          }
        }
        .logout {
            display: flex;
            margin-right: 40px;
            .image{
                position: relative;
                height: 70px;
                width: 70px;
                margin-right: 20px;
                margin-top: -5px;
            }
            .user {
                cursor: pointer;
                display:flex;
                align-items: center;
                span {
                    margin-left: 5px
                }
            }
        }


    }
</style>
<style lang="less">
    .header {
        .ant-menu {
            background: none
        }
    }
</style>