package com.example.hotel.po;
import java.time.LocalDateTime;


public class Coupon {
    /**
     * 优惠券id
     */
    private int id;
    /**
     * 优惠券描述
     */
    private String description;

    /**
     * 如果为-1 代表是网站推出的优惠
     */
    private Integer hotelId;

    /**
     * 优惠券类型 1生日特惠 2多间特惠 3满减优惠 4限时优惠 5合作企业优惠 6会员优惠
     */
    private Integer couponType;
    /**
     * 优惠券名称
     */
    private String couponName;
    /**
     * 优惠券使用门槛
     */
    private Integer targetMoney;

    private Integer targetRoom;
    /**
     * 折扣
     */
    private double discount;
    /**
     * 优惠券优惠金额
     */
    private Integer discountMoney;
    /**
     * 可用时间
     */
    private String startTime;
    /**
     * 失效时间
     */
    private String endTime;

    /**
     * 优惠券状态 是否已经失效 1可用 0失效
     */
    private Integer status;

    /**
     * 参与优惠企业
     */
    private String coopEnterprise;

    private Integer targetVip;

    public Integer getTargetVip() {
        return targetVip;
    }

    public void setTargetVip(Integer targetVip) {
        this.targetVip = targetVip;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public Integer getTargetMoney() {
        return targetMoney;
    }

    public void setTargetMoney(Integer targetMoney) {
        this.targetMoney = targetMoney;
    }

    public Integer getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(Integer discountMoney) {
        this.discountMoney = discountMoney;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getCouponType() {
        return couponType;
    }

    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Integer getTargetRoom() {
        return targetRoom;
    }

    public void setTargetRoom(Integer targetRoom) {
        this.targetRoom = targetRoom;
    }

    public String getCoopEnterprise() {
        return coopEnterprise;
    }

    public void setCoopEnterprise(String coopEnterprise) {
        this.coopEnterprise = coopEnterprise;
    }

    public Coupon() {
    }
}
