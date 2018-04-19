package com.hl.p2p.mapper;

import com.hl.p2p.pojo.Moneywithdraw;
import java.util.List;

public interface MoneywithdrawMapper {

    int insert(Moneywithdraw record);

    Moneywithdraw selectByPrimaryKey(Long id);

    List<Moneywithdraw> selectAll();

    int updateByPrimaryKey(Moneywithdraw record);
}