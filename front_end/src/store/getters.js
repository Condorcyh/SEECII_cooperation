const getters = {
  //user
  token: state => state.user.token,
  userId: state => state.user.userId,
  userInfo: state => state.user.userInfo,
  vipLevel:state=>state.user.vipLevel,
  userOrderList: state => state.user.userOrderList,
  iconChoose: state => state.user.iconChoose,
  iconChooseVisible: state => state.user.iconChooseVisible,
  creditChargeVisible: state => state.user.creditChargeVisible,
  //hotel
  isShowHistoryOrders:state=>state.hotel.isShowHistoryOrders,
  hotelListLoading: state => state.hotel.hotelListLoading,
  hotelList: state => state.hotel.hotelList,
  currentHotelInfo: state => state.hotel.currentHotelInfo,
  currentHotelId: state => state.hotel.currentHotelId,
  userComment: state => state.hotel.userComment,
  orderModalVisible: state => state.hotel.orderModalVisible,
  currentOrderRoom: state => state.hotel.currentOrderRoom,
  orderMatchCouponList: state => state.hotel.orderMatchCouponList,
  websiteAllCouponList:state => state.hotel.websiteAllCouponList,
  rankAll: state=>state.hotel.rankAll,
  typeAll: state=>state.hotel.typeAll,
  hotelListParams:state=>state.hotel.hotelListParams,
  hotelOrderList:state=>state.hotel.hotelOrderList,
  //admin
  managerList: state => state.admin.managerList,
  addManagerModalVisible: state => state.admin.addManagerModalVisible,
  addManagerParams: state => state.admin.addManagerParams,
  modifyUserInfoVisible:state => state.admin.modifyUserInfoVisible,
  //hotelManager
  orderList: state => state.hotelManager.orderList,
  addHotelModalVisible: state => state.hotelManager.addHotelModalVisible,
  addRoomModalVisible: state => state.hotelManager.addRoomModalVisible,
  modifyRoomInfoVisible:state => state.hotelManager.modifyRoomInfoVisible,
  couponVisible: state => state.hotelManager.couponVisible,
  addCouponVisible: state => state.hotelManager.addCouponVisible,
  activeHotelId: state => state.hotelManager.activeHotelId,
  couponList: state => state.hotelManager.couponList,
  hotelInfo:state => state.hotelManager.hotelInfo,


  //marketer
  allOrders:state => state.marketer.allOrders,
  websiteCoupons:state => state.marketer.websiteCoupons,
  websiteHotelId:state => state.marketer.websiteHotelId,
  vipRules:state => state.marketer.vipRules,

  };

  export default getters