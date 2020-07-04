package com.example.hotel.blImpl.order;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.bl.order.OrderService;
import com.example.hotel.bl.user.AccountService;
import com.example.hotel.data.hotel.HotelMapper;
import com.example.hotel.data.order.OrderMapper;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.po.Order;
import com.example.hotel.po.User;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.RoomVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@Service
public class OrderServiceImpl implements OrderService {
    private final static String RESERVE_ERROR = "预订失败";
    private final static String ROOMNUM_LACK = "预订房间数量剩余不足";
    private final static String ANNUL_ERROR = "撤销失败";

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    HotelService hotelService;
    @Autowired
    AccountService accountService;
    @Autowired
    HotelMapper hotelMapper;
    @Autowired
    private RoomService roomService;

    List<HotelVO> hotelList = new ArrayList<>();
    List<Order> orderList = new ArrayList<>();

    //添加订单
    @Override
    public ResponseVO addOrder(OrderVO orderVO) {
        try {
            //把当前日期转换为字符串
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(System.currentTimeMillis());
            String curdate = sf.format(date);
            orderVO.setCreateDate(curdate);
            orderVO.setOrderState("已预订");
            User user = accountService.getUserInfo(orderVO.getUserId());
            orderVO.setClientName(user.getUserName());
            orderVO.setPhoneNumber(user.getPhoneNumber());
            Order order = new Order();
            BeanUtils.copyProperties(orderVO,order);
            orderMapper.addOrder(order);
            hotelService.updateRoomInfo(orderVO.getHotelId(),orderVO.getRoomType(),orderVO.getRoomNum());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(RESERVE_ERROR);
        }
        return ResponseVO.buildSuccess(true);
    }

    //获取所有订单信息
    @Override
    public List<Order> getAllOrders() {
        return orderMapper.getAllOrders();
    }

    //获取指定用户的所有订单信息
    @Override
    public List<Order> getUserOrders(int userid) {
        return orderMapper.getUserOrders(userid);
    }

    //撤销订单
    @Override
    public ResponseVO annulOrder(int orderid) {
        //取消订单逻辑的具体实现（注意可能有和别的业务类之间的交互）
        try{
            Order order = orderMapper.getOrderById(orderid);
            int reserveRoomNum = order.getRoomNum();
            hotelService.updateRoomInfo(order.getHotelId(),order.getRoomType(),-reserveRoomNum);
            User user=accountService.getUserInfo(orderMapper.getOrderById(orderid).getUserId());
            accountService.changeCredit(user.getId(),-order.getPrice()*0.1);
            accountService.updateUserLevel(user.getId());
            orderMapper.annulOrder(orderid);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(ANNUL_ERROR);
        }
        return ResponseVO.buildSuccess(true);
    }
    //修改订单状态
    @Override
    public ResponseVO changeOrderState(int orderid,String orderState){
        Order order=orderMapper.getOrderById(orderid);
        int reserveRoomNum = order.getRoomNum();
        hotelService.updateRoomInfo(order.getHotelId(),order.getRoomType(),-reserveRoomNum);
        User user=accountService.getUserInfo(orderMapper.getOrderById(orderid).getUserId());
        if(orderState.equals("已执行"))
            accountService.changeCredit(user.getId(),order.getPrice());
        else if(orderState.equals("异常"))
            accountService.changeCredit(user.getId(),-order.getPrice()*0.5);
        accountService.updateUserLevel(user.getId());
        orderMapper.changeOrderState(orderid,orderState);
        return ResponseVO.buildSuccess(true);
    }

    public void OnlyChangeOrderState(int orderid, String orderState){
        Order order=orderMapper.getOrderById(orderid);
        orderMapper.changeOrderState(orderid,orderState);
    }

    //删除订单
    @Override
    public ResponseVO deleteOrder(int orderid){
        orderMapper.deleteOrder(orderid);
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public List<Order> getHotelOrders(Integer hotelId){
        List<Order> orders = getAllOrders();
        return orders.stream().filter(order -> order.getHotelId().equals(hotelId)).collect(Collectors.toList());
    }

    @Override
    public List<HotelVO> retrieveHotels(){
        return hotelMapper.selectAllHotel();
    }

    @Override
    public HotelVO retrieveHotelDetails(Integer hotelId){
        HotelVO hotelVO = hotelMapper.selectById(hotelId);
        List<HotelRoom> rooms = roomService.retrieveHotelRoomInfo(hotelId);
            List<RoomVO> roomVOS = rooms.stream().map(r -> {
                RoomVO roomVO = new RoomVO();
                roomVO.setId(r.getId());
                roomVO.setPrice(r.getPrice());
                roomVO.setRoomType(r.getRoomType().toString());
                roomVO.setCurNum(r.getCurNum());
                roomVO.setTotal(r.getTotal());
                roomVO.setBreakfast(r.getBreakfast());
                roomVO.setBedType(r.getBedType());
                roomVO.setWifi(r.getWifi());
                roomVO.setPeopleNum(r.getPeopleNum());
                return roomVO;
            }).collect(Collectors.toList());
            hotelVO.setRooms(roomVOS);

        return hotelVO;
    }

    @Override
    public void judge(){
        orderList = getAllOrders();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.now();
        for(Order order:orderList){
            LocalDate checkInDate = LocalDate.parse(order.getCheckInDate(),df);
            //如果订单状态是已预订，并且今天已经过了checkIn的日期
            if(order.getOrderState().equals("已预定") && today.isAfter(checkInDate)){
                //即将订单状态置为已执行
                OnlyChangeOrderState(order.getId(),"已执行");
            }
        }
    }
}
