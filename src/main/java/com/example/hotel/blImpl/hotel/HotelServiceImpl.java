package com.example.hotel.blImpl.hotel;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.bl.order.OrderService;
import com.example.hotel.bl.user.AccountService;
import com.example.hotel.data.hotel.CommentMapper;
import com.example.hotel.data.hotel.HotelMapper;
import com.example.hotel.data.order.OrderMapper;
import com.example.hotel.data.vipInfo.VipInfoMapper;
import com.example.hotel.enums.BizRegion;
import com.example.hotel.enums.HotelStar;
import com.example.hotel.enums.UserType;
import com.example.hotel.po.*;
import com.example.hotel.util.ServiceException;
import com.example.hotel.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private VipInfoMapper vipInfoMapper;

    @Autowired
    private AccountService accountService;

    @Autowired
    private OrderService orderService;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    private RoomService roomService;

    private List<HotelVO> hotelVOList;

    private List<Order> orderList;

    private boolean rateSortJudge=false;


    @Override
    public void addHotel(HotelVO hotelVO) throws ServiceException {
        System.out.println(hotelVO.getManagerId());
        User manager = accountService.getUserInfo(hotelVO.getManagerId());

        if(manager == null || !manager.getUserType().equals(UserType.Admin)){
            throw new ServiceException("管理员不存在或者无权限！创建酒店失败！");
        }
        Hotel hotel = new Hotel();
        hotel.setDescription(hotelVO.getDescription());
        hotel.setAddress(hotelVO.getAddress());
        hotel.setHotelName(hotelVO.getName());
        hotel.setPhoneNum(hotelVO.getPhoneNum());
        hotel.setManagerId(hotelVO.getManagerId());
        hotel.setRate(hotelVO.getRate());
        hotel.setBizRegion(BizRegion.valueOf(hotelVO.getBizRegion()));
        hotel.setHotelStar(HotelStar.valueOf(hotelVO.getHotelStar()));
        hotel.setVIPLevel(hotelVO.getVIPLevel());
        hotel.setVIPLevelDiscount(hotelVO.getVIPLevelDiscount());
        hotelMapper.insertHotel(hotel);
    }

    @Override
    public void deleteHotel(Integer hotelId) throws ServiceException{
        hotelMapper.deleteHotel(hotelId);
    }

    @Override
    public void updateRoomInfo(Integer hotelId, String roomType, Integer rooms) {
        roomService.updateRoomInfo(hotelId,roomType,rooms);
    }

    @Override
    public void updateHotelInfo(HotelVO hotelVO) throws ServiceException {
        Hotel hotel = new Hotel();
        hotel.setId(hotelVO.getId());
        hotel.setDescription(hotelVO.getDescription());
        hotel.setAddress(hotelVO.getAddress());
        hotel.setHotelName(hotelVO.getName());
        hotel.setPhoneNum(hotelVO.getPhoneNum());
        hotel.setManagerId(hotelVO.getManagerId());
        hotel.setRate(hotelVO.getRate());
        hotel.setBizRegion(BizRegion.valueOf(hotelVO.getBizRegion()));
        hotel.setHotelStar(HotelStar.valueOf(hotelVO.getHotelStar()));
        hotelMapper.updateHotelInfo(hotel);
    }

    @Override
    public void modifyVIPRules(VipInfoVO vipInfoVO) {
        VipInfo vipInfo=new VipInfo();
        vipInfo.setId(1);
        vipInfo.setVip1(vipInfoVO.getVip1());
        vipInfo.setVip2(vipInfoVO.getVip2());
        vipInfo.setVip3(vipInfoVO.getVip3());
        vipInfo.setVip4(vipInfoVO.getVip4());
        vipInfoMapper.updateVipInfo(vipInfo);
    }

    @Override
    public VipInfoVO getVIPRules() {
        VipInfo vipInfo=vipInfoMapper.selectById(1);
        VipInfoVO vipInfoVO=new VipInfoVO();
        vipInfoVO.setVip1(vipInfo.getVip1());
        vipInfoVO.setVip2(vipInfo.getVip2());
        vipInfoVO.setVip3(vipInfo.getVip3());
        vipInfoVO.setVip4(vipInfo.getVip4());
        return vipInfoVO;
    }

    @Override
    public int getRoomCurNum(Integer hotelId, String roomType) {
        return roomService.getRoomCurNum(hotelId,roomType);
    }

    @Override
    public List<CommentVO> getComments(Integer hotelId) {
        return commentMapper.getComments(hotelId);
    }

    @Override
    public void addComments(CommentVO commentVO) {
        Comment comment=new Comment();
        comment.setUserId(commentVO.getUserId());
        comment.setHotelId(commentVO.getHotelId());
        comment.setComment(commentVO.getComment());
        comment.setUserAvatar(commentVO.getUserAvatar());
        comment.setTime(commentVO.getTime());
        comment.setUserName(commentVO.getUserName());
        comment.setRate(commentVO.getRate());
        commentMapper.insertComment(comment);
    }

    public List<HotelVO> searchHotel(String address,String bizRegion){
        hotelVOList=orderService.retrieveHotels();
        List<HotelVO> selectedList=new ArrayList<HotelVO>();
            for(int i=0;i<hotelVOList.size();i++) {
                boolean flag = false;
                HotelVO hotelVO = hotelVOList.get(i);
                if (hotelVO.getAddress() != null&&hotelVO.getBizRegion()!=null) {
                    if (hotelVO.getAddress().contains(address) && hotelVO.getBizRegion().equals(bizRegion)) {
                        if(orderService.retrieveHotelDetails(hotelVOList.get(i).getId()).getRooms().size()!=0)
                        selectedList.add(hotelVOList.get(i));
                    }
                }
            }
        this.hotelVOList=selectedList;
        return selectedList;
    }

    public List<HotelVO> detailedSearchHotel(SearchInfVO searchInfVO){
        List<HotelVO> selectedList=new ArrayList<HotelVO>();
        for(int i=0;i<hotelVOList.size();i++){
            if(searchInfVO.getRanks().indexOf(hotelVOList.get(i).getHotelStar())!=-1){
                    if(searchInfVO.getStartRate()<=hotelVOList.get(i).getRate() && searchInfVO.getEndRate()>=hotelVOList.get(i).getRate()){
                        HotelVO HotelWithRoom=orderService.retrieveHotelDetails(hotelVOList.get(i).getId());
                        boolean PriceJudge=false;
                        List<RoomVO> roomVOList=new ArrayList<RoomVO>();
                        for(int j=0;j<HotelWithRoom.getRooms().size();j++){
                            if(HotelWithRoom.getRooms().get(j).getPrice()>=searchInfVO.getStartPrice()&&HotelWithRoom.getRooms().get(j).getPrice()<=searchInfVO.getEndPrice()){
                                PriceJudge=true;
                                roomVOList.add(HotelWithRoom.getRooms().get(j));
                            }
                        }
                        if(PriceJudge){
                            for(int t=0;t<roomVOList.size();t++){
                                if(searchInfVO.getTypes().indexOf(roomVOList.get(t).getRoomType())!=-1){
                                    selectedList.add(hotelVOList.get(i));
                                    break;
                                }
                            }
                        }
                    }
            }
        }


        this.hotelVOList=selectedList;
        return selectedList;
    }

    @Override
    public List<HotelVO> rateSortHotel() throws ServiceException {
        hotelVOList.sort(new Comparator<HotelVO>() {
            @Override
            public int compare(HotelVO o1, HotelVO o2) {
                if(o1.getRate()<o2.getRate()) {
                    return 1;
                }
                else if(o1.getRate()>o2.getRate()) {
                    return -1;
                }
                else {
                    return 0;
                }
            }
        });
        return hotelVOList;
    }

    @Override
    public List<HotelVO> priceSortHotel(boolean priceSortJudge) throws ServiceException {
        hotelVOList.sort(new Comparator<HotelVO>() {
            @Override
            public int compare(HotelVO o1, HotelVO o2) {
                HotelVO o1WithRooms=orderService.retrieveHotelDetails(o1.getId());
                HotelVO o2WithRooms=orderService.retrieveHotelDetails(o2.getId());
                List<RoomVO> Room1=o1WithRooms.getRooms();
                List<RoomVO> Room2=o2WithRooms.getRooms();
                Double Price1=getMinPrice(Room1);
                Double Price2=getMinPrice(Room2);
                if(Price1<Price2) {
                    return -1;
                }
                else if(Price1>Price2) {
                    return 1;
                }
                else {
                    return 0;
                }
            }

            public Double getMinPrice(List<RoomVO> roomVOList){
                Double Price=1000000.0;
                for(int i=0;i<roomVOList.size();i++){
                    if(roomVOList.get(i).getPrice()<Price)
                        Price=roomVOList.get(i).getPrice();
                }
                return Price;
            }
        });
        if(priceSortJudge) {
            Collections.reverse(hotelVOList);
        }
        priceSortJudge=!priceSortJudge;
        return hotelVOList;
    }

    @Override
    public List<HotelVO> retrieveHotels() {
        return hotelMapper.selectAllHotel();
    }

    @Override
    public HotelVO retrieveHotelDetails(Integer hotelId) {
        HotelVO hotelVO = hotelMapper.selectById(hotelId);
        List<HotelRoom> rooms = roomService.retrieveHotelRoomInfo(hotelId);
        List<RoomVO> roomVOS = rooms.stream().map(r -> {
            RoomVO roomVO = new RoomVO();
            roomVO.setId(r.getId());
            roomVO.setPrice(r.getPrice());
            roomVO.setRoomType(r.getRoomType().toString());
            roomVO.setCurNum(r.getCurNum());
            roomVO.setTotal(r.getTotal());
            return roomVO;
        }).collect(Collectors.toList());
        hotelVO.setRooms(roomVOS);

        return hotelVO;
    }


}
