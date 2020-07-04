package com.example.hotel.blImpl.admin;

import com.example.hotel.HotelApplication;
import com.example.hotel.bl.coupon.CouponService;
import com.example.hotel.controller.admin.AdminController;
import com.example.hotel.po.User;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserForm;
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
//桩
public class AdminServiceImplTest {
    @Autowired
    AdminController adminController;

    @BeforeClass
    public static void run() throws Exception {
        String[] args={};
        SpringApplication.run(HotelApplication.class, args);
    }

//驱动模块
    @Test
    public void AdminTest1(){
        UserForm userForm=new UserForm();
        userForm.setEmail("adminTest@qq.com");
        userForm.setPassword("123456");
        userForm.setUserType("2");
        ResponseVO responseVO=adminController.addManager(userForm);
        assertTrue(responseVO.getSuccess());
    }

    @Test
    public void AdminTest2(){
        List adminList=((List)(adminController.getAllManagers().getContent()));
        User user=(User)adminList.get(adminList.size()-1);
        assertEquals("adminTest@qq.com",user.getEmail());
        assertEquals("123456",user.getPassword());
        assertEquals("2",user.getUserType().toString());
    }
}