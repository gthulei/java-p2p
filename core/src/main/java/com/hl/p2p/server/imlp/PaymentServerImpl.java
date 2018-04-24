package com.hl.p2p.server.imlp;

import com.hl.p2p.mapper.PaymentscheduleMapper;
import com.hl.p2p.mapper.PaymentscheduledetailMapper;
import com.hl.p2p.pojo.Paymentschedule;
import com.hl.p2p.pojo.Paymentscheduledetail;
import com.hl.p2p.query.PageResult;
import com.hl.p2p.query.PaymentQueryObject;
import com.hl.p2p.server.IPaymentServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServerImpl implements IPaymentServer {

  @Autowired
  private PaymentscheduledetailMapper paymentscheduledetailMapper;

  @Autowired
  private PaymentscheduleMapper paymentscheduleMapper;

  @Override
  public PageResult getPaymentDesPage(PaymentQueryObject qo) {
    int i = paymentscheduledetailMapper.selectCount(qo);
    List<Paymentscheduledetail> list = paymentscheduledetailMapper.selectPage(qo);
    PageResult pageResult = new PageResult();
    pageResult.setPageSize(qo.getPageSize());
    pageResult.setCurrentPage(qo.getCurrentPage());
    pageResult.setTotalCount(i);
    pageResult.setData(list);
    return pageResult;
  }

  @Override
  public List<Paymentschedule> getByUserPaymentList(long userid) {
    return paymentscheduleMapper.selectByUserPaymentList(userid);
  }


}
