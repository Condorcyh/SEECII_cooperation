package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.bl.user.AccountService;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class BirthdayCouponStrategyImpl implements CouponMatchStrategy {
    @Autowired
    AccountService accountService;
    @Override
    public boolean isMatch(OrderVO orderVO, Coupon coupon) {
        //判定下订单的用户的生日是否和生日优惠相符
        if(coupon.getCouponType()==1){
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
            String date = df.format(System.currentTimeMillis());
            String orderDate=date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6,8);
            if(accountService.getUserInfo(orderVO.getUserId()).getBirthday()!=null&&accountService.getUserInfo(orderVO.getUserId()).getBirthday().substring(5).equals(orderDate.substring(5))) {
                return true;
            }
        }
        return false;
    }
}
