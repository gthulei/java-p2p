package com.hl.p2p.server;

import com.hl.p2p.pojo.Companybankinfo;
import com.hl.p2p.query.CompanybankQueryObject;
import com.hl.p2p.query.PageResult;

public interface ICompanybankServer {

  boolean addCompanybank(Companybankinfo ban);

  PageResult getCompanybankPage(CompanybankQueryObject qo);
}
