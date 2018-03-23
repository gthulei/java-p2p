package com.hl.p2p.server;

import com.hl.p2p.pojo.Userfile;

import java.util.List;

public interface IUserFileServer {

  boolean addUserFile(List<String> fileList);

  void updateUserFile(Long[] id,Long[] filetypeId);

  List<Userfile> getFiletype(Long id,boolean b);
}
