package com.hl.p2p.server;

import com.hl.p2p.pojo.Systemdictionary;
import com.hl.p2p.pojo.Systemdictionaryitem;
import com.hl.p2p.query.PageResult;
import com.hl.p2p.query.SystemdictionaryQueryObject;

import java.util.List;

public interface ISystemdictionaryServer {

  boolean saveSystemdictionary(Systemdictionary system);

  PageResult selectSystemdictionary(SystemdictionaryQueryObject qo);

  boolean systemDictionaryIdDel(String id);

  List<Systemdictionary> getSystemdictionary();

  PageResult selectSystemdictionaryitemList(SystemdictionaryQueryObject qo);

  boolean saveSystemdictionaryItem(Systemdictionaryitem systemdictionaryitem);

  boolean systemDictionaryItemIdDel(long id);

  List<Systemdictionaryitem> selectSystemdictionaryitemSn(String sn);

}
