package com.example.hotel.blImpl.order;

import com.example.hotel.HotelApplication;
import com.example.hotel.blImpl.hotel.HotelServiceImpl;
import com.example.hotel.controller.order.OrderController;
import com.example.hotel.controller.user.AccountController;
import com.example.hotel.data.hotel.RoomMapper;
import com.example.hotel.po.Hotel;
import com.example.hotel.po.Order;
import com.example.hotel.po.User;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.awt.geom.RectangularShape;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        classes = HotelApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@DirtiesContext
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderServiceImplTest {
    static Integer orderId=1;
    @Autowired
    OrderController orderController;

    @Autowired
    AccountController accountController;

    @Autowired
    RoomMapper roomMapper;

    @Autowired
    HotelServiceImpl hotelService;



    @Test
    public void OrderTest1(){
        OrderVO orderVO=new OrderVO();
        orderVO.setCheckInDate("2020-08-23");
        orderVO.setCheckOutDate("2021-06-23");
        orderVO.setClientName("后端测试");
        orderVO.setHotelId(2);
        orderVO.setOrderState("已预订");
        orderVO.setPhoneNumber("18021852796");
        orderVO.setPrice(300.0);
        orderVO.setRoomNum(3);
        orderVO.setUserId(1);
        orderVO.setHaveChild(true);
        orderVO.setHotelName("桂圆酒店");
        orderVO.setPeopleNum(2);
        orderVO.setRoomType("BigBed");
        ResponseVO responseVO=orderController.reserveHotel(orderVO);
        assertTrue(responseVO.getSuccess());
    }

    @Test
    public void OrderTest2(){
        List<Order> OrderList=(List)orderController.retrieveAllOrders().getContent();
        Order order=OrderList.get(0);
        orderId=order.getId();
        assertEquals("2020-08-23",order.getCheckInDate());
        assertEquals("2021-06-23",order.getCheckOutDate());
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        String curdate = sf.format(date);
        assertEquals(curdate,order.getCreateDate());
        assertEquals("后端测试",order.getClientName());
        assertEquals(2,(int)(order.getHotelId()));
        assertEquals("已预订",order.getOrderState());
        assertEquals("18021852796",order.getPhoneNumber());
        assertEquals(300.0,order.getPrice(),0);
        assertEquals(3,(int)order.getRoomNum());
        assertEquals(1,(int)order.getUserId());
        assertTrue(order.isHaveChild());
        assertEquals("桂圆酒店",order.getHotelName());
        assertEquals(2,(int)order.getPeopleNum());
        assertEquals("BigBed",order.getRoomType());
    }

    @Test
    public void OrderTest3(){
        List<Order> OrderList=(List)orderController.retrieveHotelOrders(2).getContent();
        Order order=OrderList.get(0);
        assertEquals("2020-08-23",order.getCheckInDate());
        assertEquals("2021-06-23",order.getCheckOutDate());
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        String curdate = sf.format(date);
        assertEquals(curdate,order.getCreateDate());
        assertEquals("后端测试",order.getClientName());
        assertEquals(2,(int)(order.getHotelId()));
        assertEquals("已预订",order.getOrderState());
        assertEquals("18021852796",order.getPhoneNumber());
        assertEquals(300.0,order.getPrice(),0);
        assertEquals(3,(int)order.getRoomNum());
        assertEquals(1,(int)order.getUserId());
        assertTrue(order.isHaveChild());
        assertEquals("桂圆酒店",order.getHotelName());
        assertEquals(2,(int)order.getPeopleNum());
        assertEquals("BigBed",order.getRoomType());
    }

    @Test
    public void OrderTest4(){
        List<Order> OrderList=(List)orderController.retrieveUserOrders(1).getContent();
        Order order=OrderList.get(0);
        assertEquals("2020-08-23",order.getCheckInDate());
        assertEquals("2021-06-23",order.getCheckOutDate());
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        String curdate = sf.format(date);
        assertEquals(curdate,order.getCreateDate());
        assertEquals("后端测试",order.getClientName());
        assertEquals(2,(int)(order.getHotelId()));
        assertEquals("已预订",order.getOrderState());
        assertEquals("18021852796",order.getPhoneNumber());
        assertEquals(300.0,order.getPrice(),0);
        assertEquals(3,(int)order.getRoomNum());
        assertEquals(1,(int)order.getUserId());
        assertTrue(order.isHaveChild());
        assertEquals("桂圆酒店",order.getHotelName());
        assertEquals(2,(int)order.getPeopleNum());
        assertEquals("BigBed",order.getRoomType());
    }

    @Test
    public void OrderTest5(){
        ResponseVO responseVO1=accountController.getUserInfo(1);
        Double creditBefore=((User)(responseVO1.getContent())).getCredit();
        ResponseVO responseVO=orderController.annulOrder(orderId);
        assertTrue(responseVO.getSuccess());
        List<Order> OrderList=(List)orderController.retrieveAllOrders().getContent();
        Order order=OrderList.get(0);
        orderId=order.getId();
        assertEquals("2020-08-23",order.getCheckInDate());
        assertEquals("2021-06-23",order.getCheckOutDate());
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        String curdate = sf.format(date);
        assertEquals(curdate,order.getCreateDate());
        assertEquals("后端测试",order.getClientName());
        assertEquals(2,(int)(order.getHotelId()));
        assertEquals("已撤销",order.getOrderState());
        assertEquals("18021852796",order.getPhoneNumber());
        assertEquals(300.0,order.getPrice(),0);
        assertEquals(3,(int)order.getRoomNum());
        assertEquals(1,(int)order.getUserId());
        assertTrue(order.isHaveChild());
        assertEquals("桂圆酒店",order.getHotelName());
        assertEquals(2,(int)order.getPeopleNum());
        assertEquals("BigBed",order.getRoomType());
        ResponseVO responseVO2=accountController.getUserInfo(1);
        Double creditAfter=((User)(responseVO2.getContent())).getCredit();
        assertEquals(30.0,creditBefore-creditAfter,0);
    }

    @Test
    public void OrderTest6(){
        ResponseVO responseVO1=accountController.getUserInfo(1);
        Double creditBefore=((User)(responseVO1.getContent())).getCredit();
        ResponseVO responseVO=orderController.changeOrderState("已执行",orderId);
        assertTrue(responseVO.getSuccess());
        List<Order> OrderList=(List)orderController.retrieveAllOrders().getContent();
        Order order=OrderList.get(0);
        orderId=order.getId();
        assertEquals("2020-08-23",order.getCheckInDate());
        assertEquals("2021-06-23",order.getCheckOutDate());
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        String curdate = sf.format(date);
        assertEquals(curdate,order.getCreateDate());
        assertEquals("后端测试",order.getClientName());
        assertEquals(2,(int)(order.getHotelId()));
        assertEquals("已执行",order.getOrderState());
        assertEquals("18021852796",order.getPhoneNumber());
        assertEquals(300.0,order.getPrice(),0);
        assertEquals(3,(int)order.getRoomNum());
        assertEquals(1,(int)order.getUserId());
        assertTrue(order.isHaveChild());
        assertEquals("桂圆酒店",order.getHotelName());
        assertEquals(2,(int)order.getPeopleNum());
        assertEquals("BigBed",order.getRoomType());
        ResponseVO responseVO2=accountController.getUserInfo(1);
        Double creditAfter=((User)(responseVO2.getContent())).getCredit();
        assertEquals(300.0,creditAfter-creditBefore,0);
    }

    @Test
    public void OrderTest7(){
        ResponseVO responseVO=orderController.deleteOrder(orderId);
        assertTrue(responseVO.getSuccess());
        List<Order> OrderList=(List)orderController.retrieveAllOrders().getContent();
        assertEquals(0,OrderList.size());
    }


    @Test
    public void OrderTest8(){
        ResponseVO responseVO=orderController.retrieveAllHotels();
        List<HotelVO> hotelVOList=(List)responseVO.getContent();
        assertEquals(1,hotelVOList.size());
        assertTrue(responseVO.getSuccess());
    }

    @Test
    public void OrderTest9(){
        ResponseVO responseVO=orderController.retrieveHotelDetail(2);
        HotelVO hotelVO=(HotelVO)responseVO.getContent();
        assertEquals(2,hotelVO.getRooms().size());
    }

}