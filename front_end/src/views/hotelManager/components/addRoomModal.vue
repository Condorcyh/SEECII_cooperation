<template>
    <a-modal
        :visible="addRoomModalVisible"
        title="录入客房"
        cancelText="取消"
        okText="确定"
        @cancel="cancel"
        @ok="handleSubmit"
    >
        <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">
            <a-form-item label="房型" v-bind="formItemLayout">
                <a-select
                    v-decorator="[
                    'roomType',
                    { rules: [{ required: true, message: '请选择房型' }] }]"
                >
                  <a-select-option value="BigBed">大床房</a-select-option>
                  <a-select-option value="DoubleBed">双床房</a-select-option>
                  <a-select-option value="Family">家庭房</a-select-option>
                    <a-select-option value="SingleBed">单人房</a-select-option>
                    <a-select-option value="Theme">主题套房</a-select-option>
                    <a-select-option value="Luxury">豪华套房</a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item label="房间数量" v-bind="formItemLayout">
                <a-input
                    placeholder="请填写房间数量"
                    v-decorator="['roomNum', { rules: [{ required: true, message: '请输入房间数量' }] }]"
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
            <a-form-item label="原始价格" v-bind="formItemLayout">
                <a-input
                    placeholder="请填写原始价格"
                    v-decorator="['price', { rules: [{ required: true, message: '请输入原始价格' }] }]"
                />
            </a-form-item>
        </a-form>
    </a-modal>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from 'vuex'
export default {
    name: 'addRoomModal',
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
    computed: {
        ...mapGetters([
            'addRoomModalVisible',
            'activeHotelId',

        ])
    },
    beforeCreate() {
        this.form = this.$form.createForm(this, { name: 'addRoomModal' });
    },
    mounted() {

    },
    methods: {
        ...mapMutations([
            'set_addRoomModalVisible',
            'set_addRoomParams',

        ]),
        ...mapActions([
            'addRoom'
        ]),
        cancel() {
            this.set_addRoomModalVisible(false)
        },
        handleSubmit(e) {
            e.preventDefault();
            this.form.validateFieldsAndScroll((err, values) => {
                if (!err) {
                    const data = {
                        roomType: this.form.getFieldValue('roomType'),
                        bedType:this.form.getFieldValue('bedType'),
                        price: Number(this.form.getFieldValue('price')),
                        total: Number(this.form.getFieldValue('roomNum')),
                        curNum: Number(this.form.getFieldValue('roomNum')),
                        peopleNum:Number(this.form.getFieldValue('peopleNum')),
                        breakfast:Number(this.form.getFieldValue('breakfast')),
                        wifi:Number(this.form.getFieldValue('wifi')),
                        hotelId: this.activeHotelId,
                    };
                    this.set_addRoomParams(data);
                    this.addRoom();
                    this.set_addRoomModalVisible(false)
                }
            });
        },
    }
}
</script>