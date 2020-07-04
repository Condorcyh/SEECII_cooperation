package com.example.hotel.controller.hotel;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.bl.order.OrderService;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.util.ServiceException;
import com.example.hotel.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;
    @Autowired
    private RoomService roomService;
//    @Autowired
//    private OrderService orderService;
//
    //添加酒店
    @PostMapping("/addHotel")
    public ResponseVO createHotel(@RequestBody HotelVO hotelVO) throws ServiceException {
        //TODO 添加酒店的后端逻辑完善
        //System.out.println(hotelVO.getName());
        hotelService.addHotel(hotelVO);
        return ResponseVO.buildSuccess(true);
    }
    //详细筛选酒店
    @PostMapping("/all")
    public ResponseVO detailedSearchHotel(@RequestBody SearchInfVO searchInfVO) throws ServiceException {
        System.out.println(searchInfVO);
        return ResponseVO.buildSuccess(hotelService.detailedSearchHotel(searchInfVO));
    }
    //更新酒店信息
    @PostMapping("/updateHotelInfo")
    public ResponseVO updateHotelInfo(@RequestBody HotelVO hotelVO) throws ServiceException{
        hotelService.updateHotelInfo(hotelVO);
        return ResponseVO.buildSuccess(true);
    }
    //修改房间信息
    @PostMapping("/modifyRoomInfo")
    public ResponseVO modifyRoomInfo(@RequestBody RoomVO roomVO) throws ServiceException{
        roomService.modifyRoomInfo(roomVO);
        return ResponseVO.buildSuccess(true);
    }
    //修改VIP规则
    @PostMapping("/modifyVIPRules")
    public ResponseVO modifyVIPRules(@RequestBody VipInfoVO vipInfoVO)throws ServiceException{
        hotelService.modifyVIPRules(vipInfoVO);
        return ResponseVO.buildSuccess(true);
    }
    //获得VIP规则
    @GetMapping("/getVIPRules")
    public ResponseVO getVIPRules()throws ServiceException{
        return ResponseVO.buildSuccess(hotelService.getVIPRules());
    }
    //根据地址筛选
    @GetMapping("/address")
    public ResponseVO searchHotel(@RequestParam("address") String address,@RequestParam("bizRegion") String bizRegion) throws ServiceException {
        return ResponseVO.buildSuccess(hotelService.searchHotel(address,bizRegion));
    }
    //按照评分排序
    @GetMapping("/rateSort")
    public ResponseVO rateSortHotel() throws ServiceException{
        return ResponseVO.buildSuccess(hotelService.rateSortHotel());
    }
    //按照价格排序
    @GetMapping("/priceSort")
    public ResponseVO priceSortHotel(@RequestParam("priceSortJudge") boolean priceSortJudge) throws ServiceException{
        return ResponseVO.buildSuccess(hotelService.priceSortHotel(priceSortJudge));
    }
    //录入房间
    @PostMapping("/roomInfo")
    public ResponseVO addRoomInfo(@RequestBody HotelRoom hotelRoom) {
        roomService.insertRoomInfo(hotelRoom);
        return ResponseVO.buildSuccess(true);
    }
    //删除酒店
    @PostMapping("/deleteHotel")
    public ResponseVO deleteHotel(@RequestParam("hotelId") Integer hotelId) throws ServiceException {
        hotelService.deleteHotel(hotelId);
        return ResponseVO.buildSuccess(true);
    }
    //添加评论
    @PostMapping("/addComment")
    public ResponseVO addComment(@RequestBody CommentVO commentVO) {
        hotelService.addComments(commentVO);
        return ResponseVO.buildSuccess(true);
    }
    //获得酒店的所有评论
    @GetMapping("/{hotelId}/comments")
    public ResponseVO getComments(@PathVariable Integer hotelId)  throws ServiceException{
        System.out.println("hotelId"+hotelId);
       return ResponseVO.buildSuccess(hotelService.getComments(hotelId));
    }
//    @PostMapping("/updateCommentLike")
//    public ResponseVO updateCommentLike(@RequestParam("commentId") Integer commentId,@RequestParam("like") Integer like) {
//        hotelService.updateCommentLike(commentId,like);
//        return ResponseVO.buildSuccess(true);
//    }
//    @PostMapping("/updateCommentDislike")
//    public ResponseVO updateCommentDislike(@RequestParam("commentId") Integer commentId,@RequestParam("dislike") Integer dislike) {
//        hotelService.updateCommentDislike(commentId,dislike);
//        return ResponseVO.buildSuccess(true);
//    }


}
