package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.bl.user.AccountService;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VipCouponStrategyImpl implements CouponMatchStrategy {
    @Autowired
    AccountService accountService;
    @Override
    public boolean isMatch(OrderVO orderVO, Coupon coupon) {
        //判定用户的VIP等级是否相符
        if(coupon.getCouponType() == 6 && accountService.getUserLevel(orderVO.getUserId()) >= coupon.getTargetVip()){
            return true;
        }
        return false;
    }
}
