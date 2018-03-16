package com.hl.p2p.server.imlp;

import com.hl.p2p.server.IEmailServer;
import com.hl.p2p.utils.BidConst;
import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;


/**
 * 发送邮件
 */
@Service
public class EmailServerImlp implements IEmailServer {

  @Value("${email.server}")
  private String server;

  @Value("${email.user}")
  private String user;

  @Value("${email.auto}")
  private String auto;

  @Value("${email.timeout}")
  private String timeout;

  @Value("${email.title}")
  private String title;

  @Value("${email.linkurl}")
  private String linkurl;


  @Override
  public void sendEmail(String target, String uuid) throws MessagingException, GeneralSecurityException {
    JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
    //邮件服务器
    senderImpl.setHost(server);
    // 建立邮件消息
    MimeMessage mailMessage = senderImpl.createMimeMessage();
    // 为防止乱码，添加编码集设置
    MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, "UTF-8");
   //收信人邮箱
    messageHelper.setTo(target);
    //发送人邮箱
    messageHelper.setFrom(user);
    //邮件主题
    messageHelper.setSubject(title);
    //构造邮件内容并且发送
    StringBuilder content = new StringBuilder(100)
      .append("这是验证邮件")
      .append("有效期是").append(BidConst.EMAIL_VALID_DAY).append("天!")
      .append("点击点击链接").append(linkurl)
      .append("bindEmail.json?uuid=").append(uuid);
    //邮件内容
    messageHelper.setText(content.toString(),true);
    //发送人服务器用户名
    senderImpl.setUsername(user);
    //此处为授权码，而非邮箱密码
    senderImpl.setPassword(auto);
    Properties prop = new Properties();
    MailSSLSocketFactory sf = new MailSSLSocketFactory();
    sf.setTrustAllHosts(true);
    //ssl安全协议
    prop.put("mail.smtp.ssl.enable", "true");
    prop.put("mail.smtp.ssl.socketFactory", sf);
    prop.put("mail.smtp.auth", "true");
    prop.put("mail.smtp.timeout", timeout);
    senderImpl.setJavaMailProperties(prop);
    senderImpl.send(mailMessage);
  }
}
