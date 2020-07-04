package com.example.hotel.po;

public class Comment {
    private Integer id;
    private Integer userId;
    private Integer hotelId;
    private String comment;
    private String userAvatar;
    private String time;
    private String userName;
    private Integer rate;

    public void setHotelId(Integer hotelId) {
        this.hotelId= hotelId;
    }
    public Integer getHotelId() {
        return hotelId;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }

    public void setUserId(Integer userId) {
        this.userId =userId;
    }
    public Integer getUserId() {
        return userId;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }
    public String getUserAvatar() {
        return userAvatar;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public String getTime() {
        return time;
    }
    public void setUserName(String userName) {
        this.userName= userName;
    }
    public String getUserName() {
        return userName;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
    public Integer getRate() {
        return rate;
    }
}
