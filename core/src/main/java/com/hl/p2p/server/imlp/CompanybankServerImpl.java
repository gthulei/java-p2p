package com.hl.p2p.server.imlp;

import com.hl.p2p.mapper.CompanybankinfoMapper;
import com.hl.p2p.pojo.Companybankinfo;
import com.hl.p2p.query.CompanybankQueryObject;
import com.hl.p2p.query.PageResult;
import com.hl.p2p.server.ICompanybankServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 平台账户管理
 */
@Service
public class CompanybankServerImpl implements ICompanybankServer {

  @Autowired
  private CompanybankinfoMapper companybankinfoMapper;

  @Override
  public boolean addCompanybank(Companybankinfo ban) {
    if(ban.getId()!=null){
      return companybankinfoMapper.updateByPrimaryKey(ban) > 0;
    }

    return companybankinfoMapper.insert(ban) > 0;
  }

  @Override
  public PageResult getCompanybankPage(CompanybankQueryObject qo) {
    int i = companybankinfoMapper.selectCount();
    List<Companybankinfo> companybankinfos = companybankinfoMapper.selectCompanybankPage(qo);
    PageResult pageResult = new PageResult();
    pageResult.setCurrentPage(qo.getCurrentPage());
    pageResult.setPageSize(qo.getPageSize());
    pageResult.setTotalCount(i);
    pageResult.setData(companybankinfos);
    return pageResult;
  }

  @Override
  public List<Companybankinfo> getCompanybankList() {
    return companybankinfoMapper.selectCompanybankAll();
  }
}
