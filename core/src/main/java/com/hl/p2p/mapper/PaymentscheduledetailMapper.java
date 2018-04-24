package com.hl.p2p.mapper;

import com.hl.p2p.pojo.Paymentscheduledetail;
import com.hl.p2p.query.PaymentQueryObject;

import java.util.List;

public interface PaymentscheduledetailMapper {

    int insert(Paymentscheduledetail record);

    int updateByPrimaryKey(Paymentscheduledetail record);

    List<Paymentscheduledetail> selectPage(PaymentQueryObject qo);

    int selectCount(PaymentQueryObject qo);
}