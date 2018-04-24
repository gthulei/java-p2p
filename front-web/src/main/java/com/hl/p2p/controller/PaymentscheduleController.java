package com.hl.p2p.controller;

import com.hl.p2p.query.PaymentQueryObject;
import com.hl.p2p.server.IPaymentServer;
import com.hl.p2p.utils.RequireLogin;
import com.hl.p2p.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PaymentscheduleController {

  @Autowired
  private IPaymentServer paymentServer;

  @RequireLogin
  @RequestMapping("/paymentschedulelist")
  public String paymentschedule(@ModelAttribute("qo") PaymentQueryObject qo, Model model){
    qo.setId(UserContext.getCurrent().getId());
    model.addAttribute("pageResult",paymentServer.getPaymentDesPage(qo));
    return "paymentschedulelist";
  }
}
