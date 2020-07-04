package com.example.hotel.blImpl.user;

import com.example.hotel.HotelApplication;
import com.example.hotel.controller.user.AccountController;
import com.example.hotel.enums.UserType;
import com.example.hotel.po.User;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserForm;
import com.example.hotel.vo.UserInfoVO;
import com.example.hotel.vo.UserVO;
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

import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        classes = HotelApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@DirtiesContext
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class AccountServiceImplTest {
    static Integer userId=0;
@Autowired
    AccountController accountController;

    @BeforeClass
    public static void run() throws Exception {
        String[] args={};
        SpringApplication.run(HotelApplication.class, args);
    }

    @Test
    public void AccountTest1(){
        UserVO userVO=new UserVO();
        userVO.setEmail("userTest@qq.com");
        userVO.setPassword("123456");
        userVO.setCredit(100);
        userVO.setUserType(UserType.Client);
       ResponseVO responseVO= accountController.registerAccount(userVO);
       assertTrue(responseVO.getSuccess());
    }

    @Test
    public void AccountTest2(){
        UserForm userForm=new UserForm();
        userForm.setEmail("userTest@qq.com");
        userForm.setPassword("123456");
        ResponseVO responseVO=accountController.login(userForm);
        User user=(User)responseVO.getContent();
        assertEquals(user.getEmail(),userForm.getEmail());
        assertEquals(user.getPassword(),userForm.getPassword());
        assertEquals(UserType.Client,user.getUserType());
        assertEquals(100,user.getCredit(),0);
        userId=user.getId();
    }

    @Test
    public void AccountTest3(){
        User user=(User)accountController.getUserInfo(userId).getContent();
        assertEquals("userTest@qq.com",user.getEmail());
        assertEquals("123456",user.getPassword());
    }

    @Test
    public void AccountTest4(){
        UserInfoVO userInfoVO=new UserInfoVO();
        userInfoVO.setUserName("后端测试");
        userInfoVO.setVipLevel(1);
        userInfoVO.setEnterprise("南京大学");
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        String date = df.format(System.currentTimeMillis());
        String orderDate=date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6,8);
        userInfoVO.setBirthday(orderDate);
        userInfoVO.setPassword("123456");
        userInfoVO.setPhoneNumber("18021852796");
        ResponseVO responseVO=accountController.updateInfo(userInfoVO,userId);
        assertTrue(responseVO.getSuccess());
        User user=(User)accountController.getUserInfo(userId).getContent();
        assertEquals("userTest@qq.com",user.getEmail());
        assertEquals(userInfoVO.getPassword(),user.getPassword());
        assertEquals(userInfoVO.getBirthday(),user.getBirthday());
        assertEquals(userInfoVO.getEnterprise(),user.getEnterprise());
        assertEquals(userInfoVO.getPhoneNumber(),user.getPhoneNumber());
        assertEquals(userInfoVO.getUserName(),user.getUserName());
    }

    @Test
    public void AccountTest5(){
        accountController.updateUserLevel(userId);
        ResponseVO responseVO=accountController.getUserLevel(userId);
        assertEquals(1,responseVO.getContent());
        accountController.creditCharge("{\"creditChargeUserID\":\"123\",\"chargeMoney\":\"50\",\"relatedInformation\":\"\"}");
    }
}