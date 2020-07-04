package com.example.hotel.blImpl.user;

import com.example.hotel.bl.user.AccountService;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.data.vipInfo.VipInfoMapper;
import com.example.hotel.po.User;
import com.example.hotel.po.VipInfo;
import com.example.hotel.vo.UserForm;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.example.hotel.enums.UserType.Admin;
import static com.example.hotel.enums.UserType.Client;


@Service
public class AccountServiceImpl implements AccountService {
    private final static String ACCOUNT_EXIST = "账号已存在";
    private final static String UPDATE_ERROR = "修改失败";
    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private VipInfoMapper vipInfoMapper;

    @Override
    public ResponseVO registerAccount(UserVO userVO) {
        userVO.setUserType(Client);
        if(userVO.getManagerCode().equals("1234")){
            userVO.setUserType(Admin);
        }
        User user = new User();
        if(accountMapper.getAccountByName(userVO.getEmail())!=null){
            return ResponseVO.buildFailure(ACCOUNT_EXIST);
        }
        BeanUtils.copyProperties(userVO,user);
        try {
            accountMapper.createNewAccount(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(ACCOUNT_EXIST);
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public User login(UserForm userForm) {
        User user = accountMapper.getAccountByName(userForm.getEmail());
        if (null == user || !user.getPassword().equals(userForm.getPassword())) {
            return null;
        }
        return user;
    }

    @Override
    public User getUserInfo(int id) {
        User user = accountMapper.getAccountById(id);
        if (user == null) {
            return null;
        }
        return user;
    }

    @Override
    public ResponseVO updateUserInfo(int id, String password, String userName, String phoneNumber,String birthday,String enterprise) {
        try {
            accountMapper.updateAccount(id, password, userName, phoneNumber,birthday,enterprise);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(UPDATE_ERROR);
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public Integer getUserLevel(int id){
        User user=accountMapper.getAccountById(id);
        return user.getVipLevel();
    }

    @Override
    public ResponseVO updateUserLevel(int id) {
        User user=accountMapper.getAccountById(id);
        try{
            VipInfo vipInfo = vipInfoMapper.selectById(1);
            if(user.getCredit() < vipInfo.getVip1()){
                accountMapper.updateLevel(id,0);
            }
            else if(user.getCredit() < vipInfo.getVip2()){
                accountMapper.updateLevel(id,1);
            }
            else if(user.getCredit() < vipInfo.getVip3()){
                accountMapper.updateLevel(id,2);
            }
            else if(user.getCredit() < vipInfo.getVip4()){
                accountMapper.updateLevel(id,3);
            }
            else{
                accountMapper.updateLevel(id,4);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(UPDATE_ERROR);
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO updateUserAvatar(Integer id, String iconChoose) {
        accountMapper.updateUserAvatar(id,iconChoose);
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO changeCredit(int id,double chargeMoney){
        try{
            User user = accountMapper.getAccountById(id);
            double credit = user.getCredit() + chargeMoney;
            accountMapper.changeCredit(id,credit);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("充值失败");
        }
        return ResponseVO.buildSuccess(true);
    }
}
