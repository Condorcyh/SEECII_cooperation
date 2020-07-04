package com.example.hotel.blImpl.hotel;

import com.example.hotel.HotelApplication;
import com.example.hotel.controller.hotel.HotelController;
import com.example.hotel.controller.order.OrderController;
import com.example.hotel.data.hotel.HotelMapper;
import com.example.hotel.enums.RoomType;
import com.example.hotel.po.Hotel;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.ResponseVO;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
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
public class HotelServiceImplTest {
    static Integer hotelId=0;
    @Autowired
    HotelController hotelController;

    @Autowired
    HotelMapper hotelMapper;

    @Autowired
    HotelServiceImpl hotelService;

    @Autowired
    OrderController orderController;

    @BeforeClass
    public static void run() throws Exception {
        String[] args={};
        SpringApplication.run(HotelApplication.class, args);
    }
    @Test
    public void HotelTest1(){
//        Hotel hotel=new Hotel();
//        hotel.setManagerId(6);
//        hotelMapper.insertHotel(hotel);
        HotelVO hotelVO=new HotelVO();
        hotelVO.setAddress("南京市栖霞区珠江路163号");
        hotelVO.setBizRegion("XiDan");
        hotelVO.setDescription("欢迎您入住");
        hotelVO.setHotelStar("Three");
        hotelVO.setManagerId(6);
        hotelVO.setName("Stark");
        hotelVO.setRate(4.3);
        hotelVO.setPhoneNum("1829553719");
        ResponseVO responseVO=new ResponseVO();
        try{
            responseVO=hotelController.createHotel(hotelVO);
            assertTrue(responseVO.getSuccess());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void HotelTest2(){
        try{
            ResponseVO responseVO=orderController.retrieveAllHotels();
            List<HotelVO> hotelVOList=(List)responseVO.getContent();
            assertEquals(2,hotelVOList.size());
            assertTrue(responseVO.getSuccess());
            hotelId=hotelVOList.get(hotelVOList.size()-1).getId();
            HotelRoom hotelRoom=new HotelRoom();
            hotelRoom.setWifi(1);
            hotelRoom.setPeopleNum(3);
            hotelRoom.setBreakfast(1);
            hotelRoom.setBedType("大床");
            hotelRoom.setRoomType(RoomType.BigBed);
            hotelRoom.setTotal(20);
            hotelRoom.setCurNum(20);
            hotelRoom.setPrice(300);
            hotelRoom.setHotelId(hotelId);
            ResponseVO responseVO1=hotelController.addRoomInfo(hotelRoom);
            assertTrue(responseVO1.getSuccess());
        List<HotelVO> hotellist=(List)hotelController.searchHotel("南京","XiDan").getContent();
        HotelVO hotelVO=hotellist.get(hotellist.size()-1);
        hotelId=hotelVO.getId();
        assertEquals("南京市栖霞区珠江路163号",hotelVO.getAddress());
        assertEquals("XiDan",hotelVO.getBizRegion());
        assertEquals("欢迎您入住",hotelVO.getDescription());
        assertEquals("Three",hotelVO.getHotelStar());
        assertEquals(6,(int)hotelVO.getManagerId());
        assertEquals("Stark",hotelVO.getName());
        assertEquals(4.3,hotelVO.getRate(),0);
        assertEquals("1829553719",hotelVO.getPhoneNum());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void HotelTest3(){
        HotelVO hotelVO=new HotelVO();
        hotelVO.setId(hotelId);
        hotelVO.setAddress("南京市栖霞区珠江路163号");
        hotelVO.setBizRegion("XiDan");
        hotelVO.setDescription("通过测试");
        hotelVO.setHotelStar("Four");
        hotelVO.setManagerId(6);
        hotelVO.setName("Stark");
        hotelVO.setRate(4.3);
        hotelVO.setPhoneNum("1829553719");
        ResponseVO responseVO=new ResponseVO();
        try{
            responseVO=hotelController.updateHotelInfo(hotelVO);
            List<HotelVO> hotelVOList=(List)hotelController.searchHotel("南京","XiDan").getContent();
            HotelVO hotel=hotelVOList.get(hotelVOList.size()-1);
//            hotelId=hotel.getId();
            assertEquals("南京市栖霞区珠江路163号",hotel.getAddress());
            assertEquals("XiDan",hotel.getBizRegion());
            assertEquals("通过测试",hotel.getDescription());
            assertEquals("Four",hotel.getHotelStar());
            assertEquals(6,(int)hotel.getManagerId());
            assertEquals("Stark",hotel.getName());
            assertEquals(4.3,hotel.getRate(),0);
            assertEquals("1829553719",hotel.getPhoneNum());

        }
        catch (Exception e){
            System.out.println("Error");
        }
        assertTrue(responseVO.getSuccess());
    }

    @Test
    public void HotelTest4(){
        try{
            ResponseVO responseVO=hotelController.deleteHotel(hotelId);
            assertTrue(responseVO.getSuccess());
            List<HotelVO> hotelVOList=(List)hotelController.searchHotel("南京","XiDan").getContent();
            HotelVO hotel=hotelVOList.get(hotelVOList.size()-1);
//            hotelId=hotel.getId();
            assertEquals("南京市栖霞区珠江路268号",hotel.getAddress());
            assertEquals("XiDan",hotel.getBizRegion());
            assertEquals("欢迎您入住",hotel.getDescription());
            assertEquals("Four",hotel.getHotelStar());
            assertEquals(6,(int)hotel.getManagerId());
            assertEquals("桂圆酒店",hotel.getName());
            assertEquals(4.8,hotel.getRate(),0);
            assertEquals("1829553719",hotel.getPhoneNum());

        }catch (Exception e){
            System.out.println("Error");
        }
    }

}