package com.example.hotel.bl.hotel;

import com.example.hotel.po.Hotel;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.po.Order;
import com.example.hotel.util.ServiceException;
import com.example.hotel.vo.*;

import java.util.List;

public interface HotelService {

    /**
     * 添加酒店
     * @param hotelVO
     * @throws
     */
    void addHotel(HotelVO hotelVO) throws ServiceException;

    /**
     * 删除酒店
     * @param hotelId
     * @throws
     */
    void deleteHotel(Integer hotelId) throws ServiceException;
    /**
     * 根据条件筛选搜索酒店(根据地址商圈搜索)
     * @param address
     * @param bizRegion
     */
    List<HotelVO> searchHotel(String address,String bizRegion) throws ServiceException;

    /**
     * 更新酒店信息
     * @param hotelVO
     * @throws
     */

    void updateHotelInfo(HotelVO hotelVO)throws ServiceException;

    /**
     * 根据条件筛选搜索酒店(根据详细信息搜索)
     * @param searchInfVO
     */
    List<HotelVO> detailedSearchHotel(SearchInfVO searchInfVO) throws ServiceException;

    /**
     * 根据页面显示的酒店评分排序
     */
    List<HotelVO> rateSortHotel() throws ServiceException;

    /**
     * 根据页面显示的房间价格（最低价）排序
     */
    List<HotelVO> priceSortHotel(boolean priceSortJudge) throws ServiceException;


    /**
     * 预订酒店修改剩余客房信息
     * @param hotelId
     * @param roomType
     * @param rooms
     */
    void updateRoomInfo(Integer hotelId, String roomType,Integer rooms);

    void modifyVIPRules(VipInfoVO vipInfoVO);

    VipInfoVO getVIPRules();

    /**
     * 列表获取酒店信息
     * @return
     */
    List<HotelVO> retrieveHotels();

    /**
     * 获取某家酒店详细信息
     * @param hotelId
     * @return
     */
    HotelVO retrieveHotelDetails(Integer hotelId);

    /**
     * 查看酒店剩余某种房间数量
     * @param hotelId
     * @param roomType
     * @return
     */
    int getRoomCurNum(Integer hotelId,String roomType);

    /**
     *获取酒店评论
     * @param hotelId
     * @return
     */

    List<CommentVO> getComments(Integer hotelId);

    /**
     * 添加酒店评论
     * @param comment
     */
    void addComments(CommentVO comment);




}
