package com.hl.p2p.mapper;

import com.hl.p2p.pojo.Moneywithdraw;
import com.hl.p2p.query.MoneyWithdrawQueryObject;

import java.util.List;

public interface MoneywithdrawMapper {

    Moneywithdraw selectByPrimaryKey(Long id);

    int insert(Moneywithdraw record);

    int updateByPrimaryKey(Moneywithdraw record);

    List<Moneywithdraw> selectPage(MoneyWithdrawQueryObject qo);

    int selectCount(MoneyWithdrawQueryObject qo);
}