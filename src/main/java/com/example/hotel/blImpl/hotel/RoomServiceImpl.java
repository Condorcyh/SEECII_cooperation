package com.example.hotel.blImpl.hotel;

import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.data.hotel.RoomMapper;
import com.example.hotel.enums.RoomType;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.vo.RoomVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomMapper roomMapper;

    @Override
    public List<HotelRoom> retrieveHotelRoomInfo(Integer hotelId) {
        return roomMapper.selectRoomsByHotelId(hotelId);
    }

    @Override
    public void insertRoomInfo(HotelRoom hotelRoom) {
        roomMapper.insertRoom(hotelRoom);
    }

    @Override
    public void updateRoomInfo(Integer hotelId, String roomType, Integer rooms) {
        roomMapper.updateRoomInfo(hotelId,roomType,rooms);
    }

    @Override
    public void modifyRoomInfo(RoomVO roomVO) {
        HotelRoom hotelRoom=new HotelRoom();
        hotelRoom.setId(roomVO.getId());
        hotelRoom.setPrice(roomVO.getPrice());
        hotelRoom.setCurNum(roomVO.getCurNum());
        hotelRoom.setTotal(roomVO.getTotal());
        hotelRoom.setRoomType(RoomType.valueOf(roomVO.getRoomType()));
        hotelRoom.setBedType(roomVO.getBedType());
        hotelRoom.setBreakfast(roomVO.getBreakfast());
        hotelRoom.setPeopleNum(roomVO.getPeopleNum());
        hotelRoom.setWifi(roomVO.getWifi());
        roomMapper.modifyRoomInfo(hotelRoom);
    }

    @Override
    public int getRoomCurNum(Integer hotelId, String roomType) {
        return roomMapper.getRoomCurNum(hotelId,roomType);
    }


}
