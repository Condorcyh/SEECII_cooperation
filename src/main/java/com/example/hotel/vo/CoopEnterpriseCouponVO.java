package com.example.hotel.vo;
/**
 * 酒店特定合作企业优惠
 */
public class CoopEnterpriseCouponVO extends CouponVO {
    private Integer hotelId;

    private String coopEnterprise;

    private Integer discountMoney;


    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getCoopEnterprise() {
        return coopEnterprise;
    }

    public void setCoopEnterprise(String coopEnterprise) {
        this.coopEnterprise = coopEnterprise;
    }

    public Integer getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(Integer discountMoney) {
        this.discountMoney = discountMoney;
    }

}
