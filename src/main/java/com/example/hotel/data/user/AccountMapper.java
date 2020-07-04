package com.example.hotel.data.user;

import com.example.hotel.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface AccountMapper {

    /**
     * 创建一个新的账号
     *
     * @return
     */
     int createNewAccount(User user);

    /**
     * 根据用户名查找账号
     * @param email
     * @return
     */
     User getAccountByName(@Param("email") String email);

     User getAccountById(@Param("id") int id);

    /**
     * 更新用户信息
     * @param id
     * @param password
     * @param userName
     * @param phoneNumber
     * @param birthday
     * @param enterprise
     * @return
     */
     int updateAccount(@Param("id") int id, @Param("password") String password,@Param("userName") String userName, @Param("phoneNumber") String phoneNumber,@Param("birthday") String birthday,@Param("enterprise") String enterprise);

    /**
     * 更新vip等级信息
     * @param id
     *
     */

    int updateLevel(@Param("id") int id,@Param("vipLevel") Integer viplevel);
    /**
     * 改变信用值
     * @param id
     * @param credit
     * @return
     */
     int changeCredit(@Param("id") int id, @Param("credit") double credit);



     void updateUserAvatar(@Param("id") int id, @Param("iconChoose") String iconChoose);

}
