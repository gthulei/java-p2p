package com.hl.p2p.server;

import javax.mail.MessagingException;
import java.security.GeneralSecurityException;

public interface IEmailServer {

   void sendEmail(String target,String uuid) throws GeneralSecurityException, MessagingException;

}
