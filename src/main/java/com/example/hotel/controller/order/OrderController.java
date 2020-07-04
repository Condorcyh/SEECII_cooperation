package com.example.hotel.controller.order;

import com.example.hotel.bl.order.OrderService;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: chenyizong
 * @Date: 2020-02-29
 */


@RestController()
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    //添加订单
    @PostMapping("/addOrder")
    public ResponseVO reserveHotel(@RequestBody OrderVO orderVO){
        return orderService.addOrder(orderVO);
    }
    //获得所有订单
    @GetMapping("/getAllOrders")
    public ResponseVO retrieveAllOrders(){

        return ResponseVO.buildSuccess(orderService.getAllOrders());
    }
    //获得用户订单
    @GetMapping("/{userid}/getUserOrders")
    public  ResponseVO retrieveUserOrders(@PathVariable int userid){
        return ResponseVO.buildSuccess(orderService.getUserOrders(userid));
    }
    //用户撤销订单
    @GetMapping("/{orderid}/annulOrder")
    public ResponseVO annulOrder(@PathVariable int orderid){
        return orderService.annulOrder(orderid);
    }
    //改变酒店订单状态
    @PostMapping("/changeOrderState")
    public ResponseVO changeOrderState(@RequestParam("orderState") String orderState,@RequestParam("orderId") Integer orderid){
        return orderService.changeOrderState(orderid,orderState);}
    //删除订单
    @PostMapping("/deleteOrder")
    public ResponseVO deleteOrder(@RequestParam("orderId") Integer orderid){return orderService.deleteOrder(orderid);}
    //获取酒店的所有订单
    @GetMapping("/{hotelId}/allOrders")
    public ResponseVO retrieveHotelOrders(@PathVariable Integer hotelId) {
        return ResponseVO.buildSuccess(orderService.getHotelOrders(hotelId));
    }

    //获得所有酒店
    @GetMapping("/all")
    public ResponseVO retrieveAllHotels(){

        return ResponseVO.buildSuccess(orderService.retrieveHotels());
    }

    //获得酒店详细信息
    @GetMapping("/{hotelId}/detail")
    public ResponseVO retrieveHotelDetail(@PathVariable Integer hotelId) {
        return ResponseVO.buildSuccess(orderService.retrieveHotelDetails(hotelId));
    }
    //判定酒店订单状态
    @GetMapping("/orderStateJudge")
    public ResponseVO orderStateJudge(){
        this.orderService.judge();
        //System.out.println("isJudged");
        return ResponseVO.buildSuccess("isJudged");
    }
}
