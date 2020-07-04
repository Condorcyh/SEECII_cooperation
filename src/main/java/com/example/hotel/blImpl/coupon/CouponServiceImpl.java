package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponService;
import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.data.coupon.CouponMapper;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CouponServiceImpl implements CouponService {

    private final  TargetMoneyCouponStrategyImpl targetMoneyCouponStrategy;
    private final TargetRoomCouponStrategyImpl targetRoomCouponStrategy;
    private final  TimeCouponStrategyImpl timeCouponStrategy;
    private final CoopEnterpriseCouponStrategyImpl coopEnterpriseCouponStrategy;
    private final BirthdayCouponStrategyImpl birthdayCouponStrategy;
    private final VipCouponStrategyImpl vipCouponStrategy;
    private final CouponMapper couponMapper;

    private List<CouponMatchStrategy> strategyList = new ArrayList<>();

    @Autowired
    public CouponServiceImpl(TargetMoneyCouponStrategyImpl targetMoneyCouponStrategy,
                             TargetRoomCouponStrategyImpl targetRoomCouponStrategy, TimeCouponStrategyImpl timeCouponStrategy,
                             CoopEnterpriseCouponStrategyImpl coopEnterpriseCouponStrategy, BirthdayCouponStrategyImpl birthdayCouponStrategy, VipCouponStrategyImpl vipCouponStrategy, CouponMapper couponMapper) {
        this.birthdayCouponStrategy = birthdayCouponStrategy;
        this.vipCouponStrategy = vipCouponStrategy;
        this.couponMapper = couponMapper;
        this.targetRoomCouponStrategy = targetRoomCouponStrategy;
        this.targetMoneyCouponStrategy = targetMoneyCouponStrategy;
        this.timeCouponStrategy = timeCouponStrategy;
        this.coopEnterpriseCouponStrategy = coopEnterpriseCouponStrategy;
        strategyList.add(targetMoneyCouponStrategy);
        strategyList.add(targetRoomCouponStrategy);
        strategyList.add(timeCouponStrategy);
        strategyList.add(coopEnterpriseCouponStrategy);
        strategyList.add(birthdayCouponStrategy);
        strategyList.add(vipCouponStrategy);
    }



    @Override
    public List<Coupon> getMatchOrderCoupon(OrderVO orderVO) {

        List<Coupon> hotelCoupons = getHotelAllCoupon(orderVO.getHotelId());

        List<Coupon> availAbleCoupons = new ArrayList<>();

        for (int i = 0; i < hotelCoupons.size(); i++) {
            for (CouponMatchStrategy strategy : strategyList) {
                //strategy调用接口的方法
                if (strategy.isMatch(orderVO, hotelCoupons.get(i))) {
                    availAbleCoupons.add(hotelCoupons.get(i));
                }
            }
        }

        return availAbleCoupons;
    }

    @Override
    public List<Coupon> getHotelAllCoupon(Integer hotelId) {
        return couponMapper.selectByHotelId(hotelId);
    }

    @Override
    public List<Coupon> getWebsiteAllCoupon() {
        return couponMapper.selectByHotelId(0);
    }

    @Override
    public boolean deleteCoupon(Integer hotelId, Integer couponId) {
        couponMapper.deleteCoupon(hotelId,couponId);
        return true;
    }

    @Override
    public CouponVO addHotelTargetMoneyCoupon(HotelTargetMoneyCouponVO couponVO) {
        Coupon coupon = new Coupon();
        coupon.setCouponName(couponVO.getName());
        coupon.setDescription(couponVO.getDescription());
        coupon.setCouponType(couponVO.getType());
        coupon.setTargetMoney(couponVO.getTargetMoney());
        coupon.setHotelId(couponVO.getHotelId());
        coupon.setDiscountMoney(couponVO.getDiscountMoney());
        coupon.setDiscount(couponVO.getDiscount());
        coupon.setStatus(1);
        int result = couponMapper.insertCoupon(coupon);
        couponVO.setId(result);
        return couponVO;
    }

    @Override
    public CouponVO addHotelTargetRoomCoupon(HotelTargetRoomCouponVO couponVO) {
        Coupon coupon = new Coupon();
        coupon.setCouponName(couponVO.getName());
        coupon.setDescription(couponVO.getDescription());
        coupon.setCouponType(couponVO.getType());
        coupon.setTargetRoom(couponVO.getTargetRoom());
        coupon.setHotelId(couponVO.getHotelId());
        coupon.setDiscountMoney(couponVO.getDiscountMoney());
        coupon.setDiscount(couponVO.getDiscount());
        coupon.setStatus(1);
        int result = couponMapper.insertCoupon(coupon);
        couponVO.setId(result);
        return couponVO;
    }
    ////
    @Override
    public CouponVO addHotelTimeCoupon(TimeCouponVO couponVO){
        Coupon coupon = new Coupon();
        coupon.setCouponName(couponVO.getName());
        coupon.setDescription(couponVO.getDescription());
        coupon.setCouponType(couponVO.getType());
        coupon.setHotelId(couponVO.getHotelId());
        coupon.setDiscountMoney(couponVO.getDiscountMoney());
        coupon.setDiscount(couponVO.getDiscount());
        coupon.setStatus(1);
        coupon.setStartTime(couponVO.getStartTime());
        coupon.setEndTime(couponVO.getEndTime());
        int result = couponMapper.insertCoupon(coupon);
        couponVO.setId(result);
        return couponVO;
    }

    @Override
    public CouponVO addCoopEnterpriseCoupon(CoopEnterpriseCouponVO couponVO) {
        Coupon coupon = new Coupon();
        coupon.setCouponName(couponVO.getName());
        coupon.setDescription(couponVO.getDescription());
        coupon.setCouponType(couponVO.getType());
        coupon.setHotelId(couponVO.getHotelId());
        coupon.setCoopEnterprise(couponVO.getCoopEnterprise());
        coupon.setDiscountMoney(couponVO.getDiscountMoney());
        coupon.setDiscount(couponVO.getDiscount());
        coupon.setStatus(1);
        int result = couponMapper.insertCoupon(coupon);
        couponVO.setId(result);
        return couponVO;
    }

    @Override
    public CouponVO addBirthdayCoupon(BirthdayCouponVO couponVO) {
        Coupon coupon = new Coupon();
        coupon.setCouponName(couponVO.getName());
        coupon.setDescription(couponVO.getDescription());
        coupon.setCouponType(couponVO.getType());
        coupon.setHotelId(couponVO.getHotelId());
        coupon.setStatus(1);
        coupon.setDiscountMoney(couponVO.getDiscountMoney());
        coupon.setDiscount(couponVO.getDiscount());
        int result=couponMapper.insertCoupon(coupon);
        couponVO.setId(result);
        return couponVO;
    }

    @Override
    public CouponVO addVipCoupon(VipCouponVO couponVO) {
        Coupon coupon=new Coupon();
        coupon.setCouponName((couponVO.getName()));
        coupon.setDescription(couponVO.getDescription());
        coupon.setCouponType(couponVO.getType());
        coupon.setHotelId(couponVO.getHotelId());
        coupon.setStatus(1);
        coupon.setDiscountMoney(couponVO.getDiscountMoney());
        coupon.setDiscount(couponVO.getDiscount());
        coupon.setTargetVip(couponVO.getTargetVip());
        int result=couponMapper.insertCoupon(coupon);
        couponVO.setId(result);
        return couponVO;
    }
}
