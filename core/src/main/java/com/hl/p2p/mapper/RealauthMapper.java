package com.hl.p2p.mapper;

import com.hl.p2p.pojo.Realauth;
import com.hl.p2p.query.PageResult;
import com.hl.p2p.query.RealauthQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RealauthMapper {

    int insert(Realauth record);

    Realauth selectById(Long id);

    int updateByPrimaryKey(Realauth record);

    List<Realauth> selectPage(RealauthQueryObject qo);

    int selectCount(RealauthQueryObject qo);
}