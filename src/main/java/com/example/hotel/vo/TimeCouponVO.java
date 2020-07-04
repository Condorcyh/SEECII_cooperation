package com.example.hotel.vo;

import java.time.LocalDateTime;

public class TimeCouponVO extends CouponVO {
    private Integer hotelId;
    private String startTime;
    private String endTime;

    //自行决定是打折还是直接减金额 如果选择打折就把targetMoney变为负数 否则就把discount变为负数

    private Integer discountMoney;


    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(Integer discountMoney) {
        this.discountMoney = discountMoney;
    }
}
