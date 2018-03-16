package com.hl.p2p.server.imlp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hl.p2p.mapper.EmailactiveMapper;
import com.hl.p2p.pojo.Emailactive;
import com.hl.p2p.server.IEmailServer;
import com.hl.p2p.server.ISendVerifyCodeServer;
import com.hl.p2p.utils.BidConst;
import com.hl.p2p.utils.DateUtil;
import com.hl.p2p.utils.HttpClientUtil;
import com.hl.p2p.utils.UserContext;
import com.hl.p2p.vo.VerifyCodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 发送短信验证码,邮箱
 */
@Service
public class SendVerifyCodeServerImpl implements ISendVerifyCodeServer {

  @Value("${sms.username}")
  private String username;

  @Value("${sms.password}")
  private String password;

  @Value("${sms.key}")
  private String key;

  @Value("${sms.url}")
  private String url;

  @Autowired
  private EmailactiveMapper emailactiveMapper;

  @Autowired
  private IEmailServer emailServer;

  @Override
  public void sendVerifyCode(String phoneNumber) {
    VerifyCodeVo vc = UserContext.getVerifyCode();
    // 规定时间内是否发送过
    if (vc != null
      && vc.getPhoneNumber().equals(phoneNumber)
      && DateUtil.getBetweenSecond(new Date(), vc.getSendTime()) < BidConst.SEND_TIME) {
      throw new RuntimeException("发送过于频繁");
    } else {
      // 生成一个验证码
      String code = UUID.randomUUID().toString().substring(0, 4);
      System.out.print(code);
      // 对接短信网关
      Map<String, String> map = new HashMap<>();
      map.put("username", username);
      map.put("password", password);
      map.put("apikey", key);
      String result = HttpClientUtil.doPostJson(url, JSON.toJSONString(map));
      Map jsonTomap = (Map) JSONObject.parse(result);
      boolean flog = (Boolean) jsonTomap.get("succeed");
      if (flog) {
        VerifyCodeVo vo = new VerifyCodeVo();
        vo.setPhoneNumber(phoneNumber);
        vo.setSendTime(new Date());
        vo.setCode(code);
        // 放入Session
        UserContext.putVerifyCode(vo);
      } else {
        throw new RuntimeException(jsonTomap.get("errorMessage").toString());
      }
    }

  }

  @Override
  public void isVerifyCode(VerifyCodeVo vo) {
    VerifyCodeVo user = UserContext.getVerifyCode();
    if (user == null) {
      throw new RuntimeException("请获取短信验证码");
    } else if (!user.getPhoneNumber().equals(vo.getPhoneNumber())) {
      throw new RuntimeException("手机号码错误");
    } else if (!user.getCode().equals(vo.getCode())) {
      throw new RuntimeException("短信验证码错误");
    } else if (DateUtil.getBetweenSecond(new Date(), vo.getSendTime()) > BidConst.SEND_TIME_EX) {
      throw new RuntimeException("短信验证码过期,请重新获取");
    }

  }

  @Override
  public void sendEmail(String email) {
    try {
      String UUID = java.util.UUID.randomUUID().toString();
      emailServer.sendEmail(email, UUID);
      Emailactive emailactive = new Emailactive();
      emailactive.setEmail(email);
      emailactive.setLogininfoId(UserContext.getCurrent().getId());
      emailactive.setSenddate(new Date());
      emailactive.setUuidcode(UUID);
      emailactiveMapper.insert(emailactive);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("发送邮件失败");
    }
  }
}
