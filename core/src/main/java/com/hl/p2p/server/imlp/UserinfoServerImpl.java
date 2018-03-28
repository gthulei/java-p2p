package com.hl.p2p.server.imlp;

import com.hl.p2p.mapper.EmailactiveMapper;
import com.hl.p2p.mapper.UserinfoMapper;
import com.hl.p2p.pojo.*;
import com.hl.p2p.server.IRealauthServer;
import com.hl.p2p.server.IUserinfoServer;
import com.hl.p2p.utils.BidConst;
import com.hl.p2p.utils.BitStatesUtils;
import com.hl.p2p.utils.DateUtil;
import com.hl.p2p.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 用户信息
 */
@Service
public class UserinfoServerImpl implements IUserinfoServer {

  @Autowired
  private UserinfoMapper userinfoMapper;

  @Autowired
  private EmailactiveMapper emailactiveMapper;

  @Autowired
  private IRealauthServer realauthServer;

  @Override
  public void updateUserInfo(Userinfo userinfo) {
    int result = userinfoMapper.updateByPrimaryKey(userinfo);
    if (result == 0) {
      throw new RuntimeException("乐观锁失败,Account:" + userinfo.getId());
    }
  }

  @Override
  public boolean addUserinfo(Userinfo userinfo) {
    int result = userinfoMapper.insert(userinfo);
    return result > 0;
  }

  @Override
  public Userinfo getUserinfoById(long id) {
    Userinfo userinfo = userinfoMapper.selectByPrimaryKey(id);
    return userinfo;
  }

  @Override
  public boolean bindPhone(String phonenumber) {
    Userinfo user = this.getUserinfoById(UserContext.getCurrent().getId());
    // 判断用户绑定没有
    if (!user.getIsBindPhone()) {
      user.setPhonenumber(phonenumber);
      // 绑定状态
      user.addState(BitStatesUtils.OP_BIND_PHONE);
      this.updateUserInfo(user);
      return true;
    } else {
      return false;
    }
  }

  @Override
  public void bindEmail(String uuid) {
    Emailactive email = emailactiveMapper.selectByUuid(uuid);
    if (email == null) {
      throw new RuntimeException("请发送邮件认证");
    }
    if (DateUtil.getBetweenSecond(new Date(), email.getSenddate()) > BidConst.SEND_MAIL_TIME) {
      throw new RuntimeException("邮件已过期,请重新发送");
    }
    Userinfo user = this.getUserinfoById(email.getLogininfoId());
    if (!user.getIsBindEmail()) {
      user.setEmail(email.getEmail());
      // 绑定状态
      user.addState(BitStatesUtils.OP_BIND_EMAIL);
      this.updateUserInfo(user);
    }
  }

  @Override
  public void saveBaseinfo(Userinfo userinfo) {
    Userinfo user = this.getUserinfoById(UserContext.getCurrent().getId());
    user.addState(BitStatesUtils.OP_BASIC_INFO);
    user.setMarriageId(userinfo.getMarriageId());
    user.setEducationbackgroundId(userinfo.getEducationbackgroundId());
    user.setIncomegradeId(userinfo.getIncomegradeId());
    user.setKidcountId(userinfo.getKidcountId());
    user.setHouseconditionId(userinfo.getHouseconditionId());
    this.updateUserInfo(user);
  }

  @Override
  public void addRealauthId(long realauthid,String idCard) {
    Userinfo user = this.getUserinfoById(UserContext.getCurrent().getId());
    user.setRealauthid(realauthid);
    user.setIdnumber(idCard);
    this.updateUserInfo(user);

  }

  @Override
  public void bindRealauth(Realauth realauth) {
    Userinfo user = this.getUserinfoById(realauth.getApplier().getId());
    Realauth realauths = realauthServer.getRealauthByid(realauth.getId());
    // 审核通过
    if(realauth.getState() == realauth.STATE_AUDIT){
      user.addState(BitStatesUtils.OP_REAL_AUTH);
      realauths.setState(realauth.STATE_AUDIT);
    }else {
      realauths.setState(realauth.STATE_REFUSE);
    }
    realauths.setAudittime(new Date());
    Logininfo logininfo = new Logininfo();
    logininfo.setId(UserContext.getCurrent().getId());
    realauths.setAuditor(logininfo);
    realauths.setRemark(realauth.getRemark());
    try {
      realauthServer.updateRealauth(realauths);
      this.updateUserInfo(user);
    }catch (Exception e){
      e.printStackTrace();
    }

  }

  @Override
  public void vedioauth(Vedioauth vo) {
    // 审核通过
    if(vo.getState() == vo.STATE_AUDIT){
      Userinfo user = this.getUserinfoById(vo.getApplier().getId());
      user.addState(BitStatesUtils.OP_VEDIO_AUTH);
      this.updateUserInfo(user);
    }
  }

  @Override
  public void updateScore(Long id,Integer score) {
    Userinfo user = this.getUserinfoById(id);
    user.setAuthscore(score);
    this.updateUserInfo(user);
  }
}
