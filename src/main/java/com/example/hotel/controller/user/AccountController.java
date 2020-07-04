package com.example.hotel.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.example.hotel.bl.user.AccountService;
import com.example.hotel.po.User;
import com.example.hotel.vo.UserForm;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserInfoVO;
import com.example.hotel.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController()
@RequestMapping("/api/user")
public class AccountController {
    private final static String ACCOUNT_INFO_ERROR = "用户名或密码错误";
    @Autowired
    private AccountService accountService;

    //登录
    @PostMapping("/login")
    public ResponseVO login(@RequestBody UserForm userForm) {
        User user = accountService.login(userForm);
        if (user == null) {
            return ResponseVO.buildFailure(ACCOUNT_INFO_ERROR);
        }
        return ResponseVO.buildSuccess(user);

    }

    //注册
    @PostMapping("/register")
    public ResponseVO registerAccount(@RequestBody UserVO userVO) {
        return accountService.registerAccount(userVO);
    }

    //获得用户个人信息
    @GetMapping("/{id}/getUserInfo")
    public ResponseVO getUserInfo(@PathVariable int id) {
        User user = accountService.getUserInfo(id);
        System.out.println(user.getId());
        if(user == null){
            return ResponseVO.buildFailure(ACCOUNT_INFO_ERROR);
        }
        return ResponseVO.buildSuccess(user);
    }

    //更新用户信息
    @PostMapping("/{id}/userInfo/update")
    public ResponseVO updateInfo(@RequestBody UserInfoVO userInfoVO,@PathVariable int id){
        return accountService.updateUserInfo(id,userInfoVO.getPassword(),userInfoVO.getUserName(),userInfoVO.getPhoneNumber(),userInfoVO.getBirthday(),userInfoVO.getEnterprise());

    }

    //获得用户等级
    @GetMapping("/{id}/getUserLevel")
    public ResponseVO getUserLevel(@PathVariable int id){
        return ResponseVO.buildSuccess(accountService.getUserLevel(id));
    }

    //更新用户等级
    @PostMapping("/{id}/updateUserLevel")
    public ResponseVO updateUserLevel(@PathVariable int id){
        return accountService.updateUserLevel(id);
    }

    //更新用户头像
    @PostMapping("/{id}/iconChoose")
    public ResponseVO iconChoose(@PathVariable Integer id,@RequestParam("iconChoose") String iconChoose) {

        return accountService.updateUserAvatar(id,iconChoose);
    }
    //修改信用值
    @PostMapping("/creditCharge")
    public ResponseVO creditCharge(@RequestBody String creditCharge) {
        //creditCharge示例：{"creditChargeUserID":"123","chargeMoney":"50","relatedInformation":""}
        //分解一下这个字符串
        JSONObject object = JSONObject.parseObject(creditCharge);
        int creditChargeUserID = Integer.parseInt(object.getString("creditChargeUserID"));
        double chargeMoney = Double.parseDouble(object.getString("chargeMoney"));

        return accountService.changeCredit(creditChargeUserID,chargeMoney);
    }

}
