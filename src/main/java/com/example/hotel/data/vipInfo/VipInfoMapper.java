package com.example.hotel.data.vipInfo;


import com.example.hotel.po.VipInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: wangbo
 * @Date: 2020-06-23
 */
@Mapper
@Repository
public interface VipInfoMapper {
    void updateVipInfo(VipInfo vipInfo);

    VipInfo selectById(@Param("id")Integer id);
}
