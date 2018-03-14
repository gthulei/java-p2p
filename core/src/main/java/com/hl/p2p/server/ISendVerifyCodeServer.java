package com.hl.p2p.server;

import com.hl.p2p.vo.VerifyCodeVo;

public interface ISendVerifyCodeServer {

 void sendVerifyCode(String phoneNumber);

 void isVerifyCode(VerifyCodeVo vo);

}
