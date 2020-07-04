package com.example.hotel.bl.order;

import com.example.hotel.po.HotelRoom;
import com.example.hotel.po.Order;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;

import java.util.List;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
public interface OrderService {

    /**
     * 预订酒店
     * @param orderVO
     * @return
     */
    ResponseVO addOrder(OrderVO orderVO);

    /**
     * 获得所有订单信息
     * @return
     */
    List<Order> getAllOrders();


    /**
     * 获得指定用户的所有订单信息
     * @param userid
     * @return
     */
    List<Order> getUserOrders(int userid);

    /**
     * 撤销订单
     * @param orderid
     * @return
     */
    ResponseVO annulOrder(int orderid);

    /**
     * 修改订单状态
     * @param orderid
     * @param orderState
     * @return
     */
    ResponseVO changeOrderState(int orderid,String orderState);

    /**
     * 删除订单
     * @param orderid
     */
    ResponseVO deleteOrder(int orderid);


    /**
     * 从HotelService移过来
     * 列表获取酒店信息
     * @return
     */
    List<HotelVO> retrieveHotels();

    /** 从HotelService移过来
     * 获取某家酒店详细信息
     * @param hotelId
     * @return
     */
    HotelVO retrieveHotelDetails(Integer hotelId);

    /**
     * 从HotelService移过来
     * @param hotelId
     * @return
     */
    List<Order> getHotelOrders(Integer hotelId);

    //判定异常订单
    void judge();
}
