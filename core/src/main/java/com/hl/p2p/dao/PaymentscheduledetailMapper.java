package com.hl.p2p.dao;

import com.hl.p2p.pojo.Paymentscheduledetail;
import java.util.List;

public interface PaymentscheduledetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Paymentscheduledetail record);

    Paymentscheduledetail selectByPrimaryKey(Long id);

    List<Paymentscheduledetail> selectAll();

    int updateByPrimaryKey(Paymentscheduledetail record);
}