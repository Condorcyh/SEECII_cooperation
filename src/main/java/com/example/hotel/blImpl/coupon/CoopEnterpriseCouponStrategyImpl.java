package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.bl.user.AccountService;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoopEnterpriseCouponStrategyImpl implements CouponMatchStrategy {
    @Autowired
    AccountService accountService;
    @Override
    public boolean isMatch(OrderVO orderVO, Coupon coupon) {
        //判定用户的企业名称是否相符
        if(coupon.getCouponType()==5){
            if(accountService.getUserInfo(orderVO.getUserId()).getEnterprise()!=null&&accountService.getUserInfo(orderVO.getUserId()).getEnterprise().equals(coupon.getCoopEnterprise())) {
                return true;
            }
        }
        return false;
    }
}
