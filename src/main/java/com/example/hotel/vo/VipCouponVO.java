package com.example.hotel.vo;

public class VipCouponVO extends CouponVO{
    private Integer hotelId;
    private Integer discountMoney;
    private Integer targetVip;
    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(Integer discountMoney) {
        this.discountMoney = discountMoney;
    }

    public Integer getTargetVip() {
        return targetVip;
    }

    public void setTargetVip(Integer targetVip) {
        this.targetVip = targetVip;
    }
}
