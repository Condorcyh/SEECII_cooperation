
<template>
    <a-list
            class="comment-list"
            item-layout="horizontal"
            :data-source="comments"
            style="border-radius: 5px"
    >
        <a-list-item slot="renderItem" slot-scope="item" style="border-bottom: 1px #f0f2f5 solid;background-color: white;border-radius: 5px;">
            <a-comment :author="item.userName" style="width: 100%;font-weight: 900;height: 100px;margin-left: 10px" :avatar="item.userAvatar?item.userAvatar:'https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png'">

                <p slot="content" style="font-weight: 500;color: black;">
                    {{ item.comment }}
                </p>
                <span slot="datetime" style="font-weight: 300; color: black;">{{item.time}}</span>

                <span slot="actions" style="font-weight: normal;position: absolute;right:10px;bottom: 15px;">
                    评分: <a-rate style="font-size: 15px;" v-model="item.rate" />
                </span>
            </a-comment>
        </a-list-item>

    </a-list>
</template>

<script>
    import { mapGetters, mapActions, mapMutations } from 'vuex'
    export default {
        props:{
            comments:{
                type: Array
            }
        },
        data() {
            return {
                likes:0,
                dislikes:0,
                action:null

            };
        },
        methods: {
            ...mapActions([
                'updateCommentLike',
                'updateCommentDislike'
            ]),

        },

    };
</script>

