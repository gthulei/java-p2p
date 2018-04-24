package com.hl.p2p.mapper;

import com.hl.p2p.pojo.Paymentschedule;
import java.util.List;

public interface PaymentscheduleMapper {

    int insert(Paymentschedule record);

    List<Paymentschedule> selectByUserPaymentList(long userid);

    int updateByPrimaryKey(Paymentschedule record);
}