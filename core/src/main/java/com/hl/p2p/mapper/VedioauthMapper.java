package com.hl.p2p.mapper;

import com.hl.p2p.pojo.Vedioauth;
import com.hl.p2p.query.VedioQueryObject;

import java.util.List;

public interface VedioauthMapper {

    int insert(Vedioauth record);

    List<Vedioauth> selectVedioPage(VedioQueryObject qo);

    int selectCount(VedioQueryObject qo);
}