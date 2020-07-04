package com.example.hotel.bl.coupon;

import com.example.hotel.po.Coupon;
import com.example.hotel.vo.*;

import java.util.List;

public interface CouponService {
    /**
     * 返回某一订单可用的优惠策略列表
     * @param orderVO
     * @return
     */
    List<Coupon> getMatchOrderCoupon(OrderVO orderVO);

    /**
     * 查看某个酒店提供的所有优惠策略（包括失效的）
     * @param hotelId
     * @return
     */
    List<Coupon> getHotelAllCoupon(Integer hotelId);

    /**
     * 查看网站提供的所有优惠策略（包括失效的）
     */
    List<Coupon> getWebsiteAllCoupon();

    /**
     * 删除某个酒店的某个优惠策略
     */

    boolean deleteCoupon(Integer hotelId,Integer couponId);
    /**
     * 添加酒店满减优惠策略
     * @param couponVO
     * @return
     */
    CouponVO addHotelTargetMoneyCoupon(HotelTargetMoneyCouponVO couponVO);

    /**
     * 添加酒店多间优惠策略
     * @param couponVO
     * @return
     */
    CouponVO addHotelTargetRoomCoupon(HotelTargetRoomCouponVO couponVO);

    /**
     * 添加酒店限时优惠策略
     * @param couponVO
     * @return
     */
    CouponVO addHotelTimeCoupon(TimeCouponVO couponVO);

    /**
     * 添加酒店合作企业优惠策略
     * @param couponVO
     * @return
     */
    CouponVO addCoopEnterpriseCoupon(CoopEnterpriseCouponVO couponVO);

    /**
     * 添加生日优惠策略
     * @param couponVO
     * @return
     */
    CouponVO addBirthdayCoupon(BirthdayCouponVO couponVO);

    /**
     * 添加vip优惠策略
     * @param couponVO
     * @return
     */
    CouponVO addVipCoupon(VipCouponVO couponVO);
}
