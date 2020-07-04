package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.OrderVO;
import org.springframework.stereotype.Service;

@Service
public class TargetRoomCouponStrategyImpl implements CouponMatchStrategy {
    /**
     * 判断某个订单是否满足某种多间金额优惠策略
     * @param orderVO
     * @param coupon
     * @return
     */
    @Override
    public boolean isMatch(OrderVO orderVO, Coupon coupon){
        //判定订单房间数量是否相符
        if(coupon.getCouponType() == 2 && orderVO.getRoomNum() >= coupon.getTargetRoom()){
            return true;
        }
        return false;
    }
}
