package com.example.hotel.vo;

import java.util.ArrayList;
import java.util.List;

public class SearchInfVO {
    private List<String> ranks;
    private List<String> types;
    private Double startRate;
    private Double endRate;
    private Double startPrice;
    private Double endPrice;
    private String startDate;
    private String endDate;
    private Integer orderState;

    public List<String> getRanks() {
        return ranks;
    }

    public void setRanks(ArrayList<String> ranks) {
        this.ranks = ranks;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }

    public Double getStartRate() {
        return startRate;
    }

    public void setStartRate(Double startRate) {
        this.startRate = startRate;
    }

    public Double getEndRate() {
        return endRate;
    }

    public void setEndRate(Double endRate) {
        this.endRate = endRate;
    }

    public Double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Double startPrice) {
        this.startPrice = startPrice;
    }

    public Double getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(Double endPrice) {
        this.endPrice = endPrice;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }
}
