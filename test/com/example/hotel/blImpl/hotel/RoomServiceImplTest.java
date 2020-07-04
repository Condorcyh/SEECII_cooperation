package com.example.hotel.blImpl.hotel;

import com.example.hotel.HotelApplication;
import com.example.hotel.controller.hotel.HotelController;
import com.example.hotel.data.hotel.RoomMapper;
import com.example.hotel.enums.RoomType;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.RoomVO;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        classes = HotelApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@DirtiesContext
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoomServiceImplTest {
    static Integer roomId=0;
    @Autowired
    HotelController hotelController;

    @Autowired
    RoomServiceImpl roomService;

    @Autowired
    RoomMapper roomMapper;

    @Test
    public void RoomTest1(){
        HotelRoom hotelRoom=new HotelRoom();
        hotelRoom.setWifi(1);
        hotelRoom.setPeopleNum(3);
        hotelRoom.setBreakfast(1);
        hotelRoom.setBedType("大床");
        hotelRoom.setRoomType(RoomType.BigBed);
        hotelRoom.setTotal(20);
        hotelRoom.setCurNum(20);
        hotelRoom.setPrice(300);
        hotelRoom.setHotelId(4);
        ResponseVO responseVO=hotelController.addRoomInfo(hotelRoom);
        assertTrue(responseVO.getSuccess());
    }

    @Test
    public void RoomTest2(){
        List<HotelRoom> roomList=roomService.retrieveHotelRoomInfo(4);
        HotelRoom hotelRoom=roomList.get(roomList.size()-1);
        roomId=hotelRoom.getId();
        assertEquals(25,hotelRoom.getCurNum());
        assertEquals(30,hotelRoom.getTotal());
        assertEquals(300,hotelRoom.getPrice(),0);
        assertEquals("大床房",hotelRoom.getRoomType().toString());
//        boolean judge=false;
//        List<HotelRoom> roomList=roomMapper.selectRoomsByHotelId(1);
//        if(roomList.size()!=0)
//            judge=true;
//        assertTrue(judge);
    }

    @Test
    public void RoomTest3(){
        RoomVO roomVO=new RoomVO();
        roomVO.setCurNum(30);
        roomVO.setId(roomId);
        roomVO.setPrice(200.0);
        roomVO.setRoomType("BigBed");
        roomVO.setTotal(30);
        roomVO.setBreakfast(1);
        roomVO.setBedType("大床");
        roomVO.setPeopleNum(2);
        roomVO.setWifi(1);
        ResponseVO responseVO=new ResponseVO();
        try{
        responseVO=hotelController.modifyRoomInfo(roomVO);}
        catch (Exception e){
            System.out.println("Error");
        }
        assertTrue(responseVO.getSuccess());
    }

    @Test
    public void RoomTest4(){
        roomService.updateRoomInfo(4,"BigBed",5);
        List<HotelRoom> roomList=roomService.retrieveHotelRoomInfo(4);
        HotelRoom hotelRoom=roomList.get(roomList.size()-1);
        assertEquals(25,hotelRoom.getCurNum());
    }

    @Test
    public void RoomTest5(){
        assertEquals(25,roomService.getRoomCurNum(4,"BigBed"));
    }
}