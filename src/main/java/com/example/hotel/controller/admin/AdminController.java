package com.example.hotel.controller.admin;

import com.example.hotel.bl.admin.AdminService;
import com.example.hotel.blImpl.admin.AdminServiceImpl;
import com.example.hotel.util.ServiceException;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@RestController()
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    //添加酒店工作人员和营销人员的接口
    @PostMapping("/addManager")
    public ResponseVO addManager(@RequestBody UserForm userForm){
        return adminService.addManager(userForm);
    }
    //获得所有用户的信息
    @PostMapping("/getAllManagers")
    public ResponseVO getAllManagers(){
        return ResponseVO.buildSuccess(adminService.getAllManagers());
    }
    //删除用户
    @PostMapping("/deleteUser")
    public ResponseVO deleteHotel(@RequestParam("userId") Integer id) throws ServiceException {
        adminService.deleteUser(id);
        return ResponseVO.buildSuccess(true);
    }


}
