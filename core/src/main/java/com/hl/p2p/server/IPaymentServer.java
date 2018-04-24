package com.hl.p2p.server;

import com.hl.p2p.pojo.Paymentschedule;
import com.hl.p2p.query.PageResult;
import com.hl.p2p.query.PaymentQueryObject;

import java.util.List;

public interface IPaymentServer {

  PageResult getPaymentDesPage(PaymentQueryObject qo);

  List<Paymentschedule> getByUserPaymentList(long userid);

}
