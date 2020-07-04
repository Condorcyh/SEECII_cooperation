package com.example.hotel.vo;

/**
 * @author huwen
 * @date 2019/3/23
 */
public class UserForm {
    /**
     * 用户邮箱，不可重复
     */
    private String email;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户类型
     */
    private String userType;

    /**
     * 所属酒店ID
     */
    private Integer hotelId;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Integer getHotelId(){
        return hotelId;
    }
    public void setHotelId(Integer hotelId){
        this.hotelId=hotelId;
    }
}
