package com.example.hotel.enums;

public enum RoomType {
    BigBed("大床房"),
    DoubleBed("双床房"),
    Family("家庭房"),
    Luxury("豪华套房"),
    Theme("主题套房"),
    SingleBed("单人房");
    private String value;

    RoomType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
