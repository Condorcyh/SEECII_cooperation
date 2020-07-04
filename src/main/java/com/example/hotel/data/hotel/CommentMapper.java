package com.example.hotel.data.hotel;

import com.example.hotel.po.Comment;
import com.example.hotel.po.Hotel;
import com.example.hotel.vo.CommentVO;
import com.example.hotel.vo.HotelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {

    List<CommentVO> getComments(@Param("hotelId") Integer hotelId);
    void insertComment(Comment comment);
}