package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.bl.user.AccountService;
import com.example.hotel.blImpl.user.AccountServiceImpl;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.OrderVO;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TimeCouponStrategyImpl implements CouponMatchStrategy {
    /**
     * 判断某个订单是否满足某种限时优惠策略
     * @param orderVO
     * @return
     */
    @Override
    public boolean isMatch(OrderVO orderVO, Coupon coupon) {
        //判定订单的预订时间是否相符
        if(coupon.getCouponType()==4){
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
            String date = df.format(System.currentTimeMillis());
            String orderDate=date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6,8);
            if(Integer.parseInt(orderDate.substring(0,4))>=Integer.parseInt(coupon.getStartTime().substring(0,4))&&Integer.parseInt(orderDate.substring(0,4))<=Integer.parseInt(coupon.getEndTime().substring(0,4))){
                if(Integer.parseInt(orderDate.substring(5,7))>Integer.parseInt(coupon.getStartTime().substring(5,7))&&Integer.parseInt(orderDate.substring(5,7))<Integer.parseInt(coupon.getEndTime().substring(5,7))) {
                    return true;
                }
                else if(Integer.parseInt(orderDate.substring(5,7))==Integer.parseInt(coupon.getStartTime().substring(5,7))&&Integer.parseInt(orderDate.substring(5,7))==Integer.parseInt(coupon.getEndTime().substring(5,7))) {
                    if (Integer.parseInt(orderDate.substring(8, 10)) >= Integer.parseInt(coupon.getStartTime().substring(8, 10)) && Integer.parseInt(orderDate.substring(8, 10)) <= Integer.parseInt(coupon.getEndTime().substring(8, 10))) {
                        return true;
                    }
                }
                else if(Integer.parseInt(orderDate.substring(5,7))==Integer.parseInt(coupon.getStartTime().substring(5,7))){
                    if(Integer.parseInt(orderDate.substring(8, 10)) >= Integer.parseInt(coupon.getStartTime().substring(8, 10))) {
                        return true;
                    }
                }
                else if(Integer.parseInt(orderDate.substring(5,7))==Integer.parseInt(coupon.getEndTime().substring(5,7))){
                    if( Integer.parseInt(orderDate.substring(8, 10)) <= Integer.parseInt(coupon.getEndTime().substring(8, 10))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
