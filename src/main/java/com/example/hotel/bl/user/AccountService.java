package com.example.hotel.bl.user;

import com.example.hotel.po.User;
import com.example.hotel.vo.*;

/**
 * @author huwen
 * @date 2019/3/23
 */
public interface AccountService {

    /**
     * 注册账号
     *
     * @return
     */
    ResponseVO registerAccount(UserVO userVO);

    /**
     * 用户登录，登录成功会将用户信息保存再session中
     *
     * @return
     */
    User login(UserForm userForm);

    /**
     * 获取用户个人信息
     * @param id
     * @return
     */
    User getUserInfo(int id);

    /**
     * 更新用户个人信息
     * @param id
     * @param password
     * @param userName
     * @param phoneNumber
     * @return
     */
    ResponseVO updateUserInfo(int id, String password,String userName,String phoneNumber,String birthday,String enterprise);

    /**
     * 获取用户个人vip等级信息
     * @param id
     * @return
     */
    Integer getUserLevel(int id);

    /**
     * 充值改变信用
     * @param id
     * @param chargeMoney
     * @return
     */
    ResponseVO changeCredit(int id,double chargeMoney);
    /**
     * 更新用户个人vip信息
     * @param id
     * @return
     */

    ResponseVO updateUserLevel(int id);

    /**
     * 更新头像
     * @param id
     * @param iconChoose
     * @return
     */
    ResponseVO updateUserAvatar(Integer id,String iconChoose);
}
