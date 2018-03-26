package com.hl.p2p.server.imlp;

import com.hl.p2p.mapper.UserfileMapper;
import com.hl.p2p.pojo.Userfile;
import com.hl.p2p.query.PageResult;
import com.hl.p2p.query.UserFileQueryObject;
import com.hl.p2p.server.IUserFileServer;
import com.hl.p2p.server.IUserinfoServer;
import com.hl.p2p.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 风控资料
 */
@Service
public class UserFileServerImpl implements IUserFileServer{

  @Autowired
  private UserfileMapper userfileMapper;

  @Autowired
  private IUserinfoServer userinfoServer;

  @Override
  public boolean addUserFile(List<String> fileList) {
    ArrayList<Userfile> userfiles = new ArrayList<>();
    for (String item : fileList){
      userfiles.add(new Userfile(Userfile.STATE_AUDIT, UserContext.getCurrent().getId(),item));
    }
    return userfileMapper.insetList(userfiles) > 0;
  }

  @Override
  public void updateUserFile(Long[] id,Long[] filetypeId) {
    for (int i = 0;i<id.length;i++){
      Userfile userfile = new Userfile();
      userfile.setId(id[i]);
      userfile.setFiletypeId(filetypeId[i]);
      try {
        userfileMapper.updateByPrimaryKey(userfile);
      }catch (Exception e){
        e.printStackTrace();
      }

    }
  }

  @Override
  public void updateUserFileAuth(Userfile userfile) {
    userfile.setAuditorId(UserContext.getCurrent().getId());
    userfile.setAudittime(new Date());
    userfileMapper.updateByPrimaryKey(userfile);
    userinfoServer.updateScore(userfile.getApplierId(),userfileMapper.selectScore(userfile.getApplierId()));
  }

  // 未指定风控类型
  @Override
  public List<Userfile> getFiletype(Long id,boolean b) {
    return userfileMapper.selectFiletype(id,b);
  }

  @Override
  public PageResult getUserFileList(UserFileQueryObject qo) {
    int i = userfileMapper.selectCount(qo);
    List<Userfile> userfiles = userfileMapper.selectPage(qo);
    PageResult pageResult = new PageResult();
    pageResult.setData(userfiles);
    pageResult.setTotalCount(i);
    pageResult.setCurrentPage(qo.getCurrentPage());
    pageResult.setPageSize(qo.getPageSize());
    return pageResult;
  }

  @Override
  public Userfile getUserFileById(Long id) {
    return userfileMapper.selectByPrimaryKey(id);
  }

}
