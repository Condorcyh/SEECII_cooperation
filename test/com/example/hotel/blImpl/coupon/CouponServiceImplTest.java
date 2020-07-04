package com.example.hotel.blImpl.coupon;

import com.example.hotel.HotelApplication;
import com.example.hotel.config.CORSConfig;
import com.example.hotel.controller.coupon.CouponController;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.*;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        classes = HotelApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@DirtiesContext
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class CouponServiceImplTest {
    static ArrayList<String> couponIdList=new ArrayList<>();
@Autowired
    CouponController couponController;

    @BeforeClass
    public static void run() throws Exception {
        String[] args={};
        SpringApplication.run(HotelApplication.class, args);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void CouponTest1() {

        boolean judge=true;
        HotelTargetMoneyCouponVO couponVO=new HotelTargetMoneyCouponVO();
        couponVO.setName("testTargetMoney");
        couponVO.setDescription("it's a TargetMoneyCoupon for test");
        couponVO.setType(3);
        couponVO.setTargetMoney(150);
        couponVO.setHotelId(1);
        couponVO.setDiscountMoney(100);
        couponVO.setDiscount(-1);
        ResponseVO responseVO=couponController.addHotelTargetMoneyCoupon(couponVO);
        if(!responseVO.getSuccess()){
            judge=false;
        }
        else{
            couponIdList.add(couponVO.getName());
        }
//        else{
//            ResponseVO responseVO1=couponController.getOrderMatchCoupons(1,1,300.0,2,"2020-06-19","2020-06-30");
//            List<Coupon> couponList=(List)responseVO1.getContent();
//            int num=0;
//            for(int i=0;i<couponList.size();i++){
//                if(couponList.get(i).getCouponType()==3) {
//                    num = i;
//                    break;
//                }
//            }
//            Coupon couponData=couponList.get(num);
//            assertEquals(couponVO.getName(),couponData.getCouponName());
//            assertEquals(couponVO.getDescription(),couponData.getDescription());
//            assertEquals(couponVO.getType(),couponData.getCouponType());
//            assertEquals(couponVO.getTargetMoney(),couponVO.getTargetMoney());
//            assertEquals(couponVO.getHotelId(),couponData.getHotelId());
//            assertEquals(couponVO.getDiscountMoney(),couponData.getDiscountMoney());
//            assertEquals(couponVO.getDiscount(),couponData.getDiscount(),0);
//            assertEquals((Integer)1,couponData.getStatus());
//            couponController.deleteCoupon(couponData.getHotelId(),couponData.getId());
//        }
        assertTrue(judge);

    }

    @Test
    public void CouponTest2() {

        boolean judge=true;
        HotelTargetRoomCouponVO couponVO=new HotelTargetRoomCouponVO();
        couponVO.setName("testTargetRoom");
        couponVO.setDescription("it's a TargetRoomCoupon for test");
        couponVO.setType(2);
        couponVO.setTargetRoom(2);
        couponVO.setHotelId(1);
        couponVO.setDiscountMoney(-1);
        couponVO.setDiscount(0.25);
        ResponseVO responseVO=couponController.addHotelTargetRoomCoupon(couponVO);
        if(!responseVO.getSuccess()) {
            judge = false;
        }
        else{
            couponIdList.add(couponVO.getName());}

//        else{
//            ResponseVO responseVO1=couponController.getOrderMatchCoupons(1,1,300.0,2,"2020-06-19","2020-06-20");
//            List<Coupon> couponList=(List)responseVO1.getContent();
//            int num=0;
//            for(int i=0;i<couponList.size();i++){
//                if(couponList.get(i).getCouponType()==2) {
//                    num = i;
//                    break;
//                }
//            }
//            Coupon couponData=couponList.get(num);
//            assertEquals(couponVO.getName(),couponData.getCouponName());
//            assertEquals(couponVO.getDescription(),couponData.getDescription());
//            assertEquals(couponVO.getType(),couponData.getCouponType());
//            assertEquals(couponVO.getTargetRoom(),couponData.getTargetRoom());
//            assertEquals(couponVO.getHotelId(),couponData.getHotelId());
//            assertEquals(couponVO.getDiscountMoney(),couponData.getDiscountMoney());
//            assertEquals(couponVO.getDiscount(),couponData.getDiscount(),0);
//            assertEquals((Integer)1,couponData.getStatus());
//            couponController.deleteCoupon(couponData.getHotelId(),couponData.getId());
//        }
        assertTrue(judge);
    }

    @Test
    public void CouponTest3() {

        boolean judge=true;
        TimeCouponVO couponVO=new TimeCouponVO();
        couponVO.setName("testTime");
        couponVO.setDescription("it's a TimeCoupon for test");
        couponVO.setType(4);

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        String date = df.format(System.currentTimeMillis());
        String orderDate=date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6,8);
        couponVO.setStartTime(orderDate);
        couponVO.setEndTime(orderDate);
        couponVO.setHotelId(1);
        couponVO.setDiscountMoney(-1);
        couponVO.setDiscount(0.25);
        ResponseVO responseVO=couponController.addHotelTimeCoupon(couponVO);
        if(!responseVO.getSuccess()) {
            judge = false;
        }
        else{
            couponIdList.add(couponVO.getName());}
//        else{
//            ResponseVO responseVO1=couponController.getOrderMatchCoupons(1,1,300.0,2,"2020-06-19","2020-06-30");
//            List<Coupon> couponList=(List)responseVO1.getContent();
//            int num=0;
//            for(int i=0;i<couponList.size();i++){
//                if(couponList.get(i).getCouponType()==4) {
//                    num = i;
//                    break;
//                }
//            }
//            Coupon couponData=couponList.get(num);
//            assertEquals(couponVO.getName(),couponData.getCouponName());
//            assertEquals(couponVO.getDescription(),couponData.getDescription());
//            assertEquals(couponVO.getType(),couponData.getCouponType());
//            assertEquals(couponVO.getStartTime(),couponData.getStartTime().substring(0,10));
//            assertEquals(couponVO.getEndTime(),couponData.getEndTime().substring(0,10));
//            assertEquals(couponVO.getHotelId(),couponData.getHotelId());
//            assertEquals(couponVO.getDiscountMoney(),couponData.getDiscountMoney());
//            assertEquals(couponVO.getDiscount(),couponData.getDiscount(),0);
//            assertEquals((Integer)1,couponData.getStatus());
//            couponController.deleteCoupon(couponData.getHotelId(),couponData.getId());
//        }
        assertTrue(judge);
    }

    @Test
    public void CouponTest4() {

        boolean judge=true;
        CoopEnterpriseCouponVO couponVO=new CoopEnterpriseCouponVO();
        couponVO.setName("testCoopEnterprise");
        couponVO.setDescription("it's a CoopEnterpriseCoupon for test");
        couponVO.setType(5);
        couponVO.setCoopEnterprise("南京大学");
        couponVO.setHotelId(1);
        couponVO.setDiscountMoney(-1);
        couponVO.setDiscount(0.25);
        ResponseVO responseVO=couponController.addCoopEnterpriseCoupon(couponVO);
        if(!responseVO.getSuccess()) {
            judge = false;
        }
        else{
            couponIdList.add(couponVO.getName());}
//        else{
//            ResponseVO responseVO1=couponController.getOrderMatchCoupons(1,1,300.0,2,"2020-06-19","2020-06-30");
//            List<Coupon> couponList=(List)responseVO1.getContent();
//            int num=0;
//            for(int i=0;i<couponList.size();i++){
//                if(couponList.get(i).getCouponType()==5) {
//                    num = i;
//                    break;
//                }
//            }
//            Coupon couponData=couponList.get(num);
//            assertEquals(couponVO.getName(),couponData.getCouponName());
//            assertEquals(couponVO.getDescription(),couponData.getDescription());
//            assertEquals(couponVO.getType(),couponData.getCouponType());
//            assertEquals(couponVO.getCoopEnterprise(),couponData.getCoopEnterprise());
//            assertEquals(couponVO.getHotelId(),couponData.getHotelId());
//            assertEquals(couponVO.getDiscountMoney(),couponData.getDiscountMoney());
//            assertEquals(couponVO.getDiscount(),couponData.getDiscount(),0);
//            assertEquals((Integer)1,couponData.getStatus());
//            couponController.deleteCoupon(couponData.getHotelId(),couponData.getId());
//        }
        assertTrue(judge);
    }

    @Test
    public void CouponTest5() {

        boolean judge=true;
        BirthdayCouponVO couponVO=new BirthdayCouponVO();
        couponVO.setName("testBirthday");
        couponVO.setDescription("it's a BirthdayCoupon for test");
        couponVO.setType(1);
        couponVO.setHotelId(1);
        couponVO.setDiscountMoney(-1);
        couponVO.setDiscount(0.25);
        ResponseVO responseVO=couponController.addBirthdayCoupon(couponVO);
        if(!responseVO.getSuccess()) {
            judge = false;
        }
        else{
            couponIdList.add(couponVO.getName());}
//        else{
//            ResponseVO responseVO1=couponController.getOrderMatchCoupons(1,1,300.0,2,"2020-06-19","2020-06-20");
//            List<Coupon> couponList=(List)responseVO1.getContent();
//            int num=0;
//            for(int i=0;i<couponList.size();i++){
//                if(couponList.get(i).getCouponType()==1) {
//                    num = i;
//                    break;
//                }
//            }
//            Coupon couponData=couponList.get(num);
//            assertEquals(couponVO.getName(),couponData.getCouponName());
//            assertEquals(couponVO.getDescription(),couponData.getDescription());
//            assertEquals(couponVO.getType(),couponData.getCouponType());
//            assertEquals(couponVO.getHotelId(),couponData.getHotelId());
//            assertEquals(couponVO.getDiscountMoney(),couponData.getDiscountMoney());
//            assertEquals(couponVO.getDiscount(),couponData.getDiscount(),0);
//            assertEquals((Integer)1,couponData.getStatus());
//            couponController.deleteCoupon(couponData.getHotelId(),couponData.getId());
//        }
        assertTrue(judge);
    }

    @Test
    public void CouponTest6() {

        boolean judge=true;
        VipCouponVO couponVO=new VipCouponVO();
        couponVO.setName("testVip");
        couponVO.setDescription("it's a VipCoupon for test");
        couponVO.setType(6);
        couponVO.setTargetVip(0);
        couponVO.setHotelId(1);
        couponVO.setDiscountMoney(-1);
        couponVO.setDiscount(0.25);
        ResponseVO responseVO=couponController.addVipCoupon(couponVO);
        if(!responseVO.getSuccess()) {
            judge = false;
        }
        else{
            couponIdList.add(couponVO.getName());}
//        else{
//            ResponseVO responseVO1=couponController.getOrderMatchCoupons(1,1,300.0,2,"2020-06-19","2020-06-20");
//            List<Coupon> couponList=(List)responseVO1.getContent();
//            int num=0;
//            for(int i=0;i<couponList.size();i++){
//                if(couponList.get(i).getCouponType()==6) {
//                    num = i;
//                    break;
//                }
//            }
//            Coupon couponData=couponList.get(num);
//            assertEquals(couponVO.getName(),couponData.getCouponName());
//            assertEquals(couponVO.getDescription(),couponData.getDescription());
//            assertEquals(couponVO.getType(),couponData.getCouponType());
//            assertEquals(couponVO.getTargetVip(),couponData.getTargetVip());
//            assertEquals(couponVO.getHotelId(),couponData.getHotelId());
//            assertEquals(couponVO.getDiscountMoney(),couponData.getDiscountMoney());
//            assertEquals(couponVO.getDiscount(),couponData.getDiscount(),0);
//            assertEquals((Integer)1,couponData.getStatus());
//            couponController.deleteCoupon(couponData.getHotelId(),couponData.getId());
//        }
        assertTrue(judge);
   }

    @Test
    public void CouponTest7() {
        ResponseVO responseVO=couponController.getHotelAllCoupons(1);
        List<Coupon> couponList=(List)responseVO.getContent();
        int count=0;
        List<Coupon> couponToBeDeleted=new ArrayList<>();
        for(int i=0;i<couponList.size();i++){
            if(couponIdList.indexOf(couponList.get(i).getCouponName())!=-1){
                count+=1;
                couponToBeDeleted.add(couponList.get(i));
            }
        }
        assertEquals(6,count);
    }

    @Test
    public void CouponTest8() {
        ResponseVO responseVO=couponController.getOrderMatchCoupons(1,1,300.0,2,"2020-01-19","2020-12-20");
        List<Coupon> couponList=(List)responseVO.getContent();
        int count=0;
        List<Coupon> couponToBeDeleted=new ArrayList<>();
        for(int i=0;i<couponList.size();i++){
            if(couponIdList.indexOf(couponList.get(i).getCouponName())!=-1){
                count+=1;
                couponToBeDeleted.add(couponList.get(i));
            }
        }
        assertEquals(5,count);

        for(int i=0;i<couponToBeDeleted.size();i++){
            couponController.deleteCoupon(couponToBeDeleted.get(i).getHotelId(),couponToBeDeleted.get(i).getId());
        }
    }


}