package com.example.hotel.data.order;

import com.example.hotel.po.Order;
import com.example.hotel.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@Mapper
@Repository
//TODO 完成取消订单的数据库操作
//完成取消订单的数据库操作
public interface OrderMapper {

    int addOrder(Order order);

    List<Order> getAllOrders();

    List<Order> getUserOrders(@Param("userid") int userid);

    List<Order> getAllHotelOrders(@Param("hotelId") int hotelId);

    int annulOrder(@Param("orderid") int orderid);

    Order getOrderById(@Param("orderid") int orderid);

    int changeOrderState(@Param("orderid") int orderid,@Param("orderState") String orderState);

    int deleteOrder(@Param("orderid") int orderid);
}
