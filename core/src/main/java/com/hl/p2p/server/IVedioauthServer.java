package com.hl.p2p.server;

import com.hl.p2p.pojo.Vedioauth;
import com.hl.p2p.query.PageResult;
import com.hl.p2p.query.VedioQueryObject;

public interface IVedioauthServer {

  boolean addVedioauth(Vedioauth vedioauth);

  PageResult getVedioPage(VedioQueryObject qo);
}
