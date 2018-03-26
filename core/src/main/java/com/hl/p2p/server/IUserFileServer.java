package com.hl.p2p.server;

import com.hl.p2p.pojo.Userfile;
import com.hl.p2p.query.PageResult;
import com.hl.p2p.query.UserFileQueryObject;

import java.util.List;

public interface IUserFileServer {

  boolean addUserFile(List<String> fileList);

  void updateUserFile(Long[] id,Long[] filetypeId);

  void updateUserFileAuth(Userfile userfile);

  List<Userfile> getFiletype(Long id,boolean b);

  PageResult getUserFileList(UserFileQueryObject qo);

  Userfile getUserFileById(Long id);
  
}
