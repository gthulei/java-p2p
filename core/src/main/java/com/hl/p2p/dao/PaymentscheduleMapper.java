package com.hl.p2p.dao;

import com.hl.p2p.pojo.Paymentschedule;
import java.util.List;

public interface PaymentscheduleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Paymentschedule record);

    Paymentschedule selectByPrimaryKey(Long id);

    List<Paymentschedule> selectAll();

    int updateByPrimaryKey(Paymentschedule record);
}