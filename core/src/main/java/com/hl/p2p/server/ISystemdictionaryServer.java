package com.hl.p2p.server;

import com.hl.p2p.pojo.Systemdictionary;
import com.hl.p2p.query.PageResult;
import com.hl.p2p.query.SystemdictionaryQueryObject;

public interface ISystemdictionaryServer {

  boolean saveSystemdictionary(Systemdictionary system);

  PageResult selectSystemdictionary(SystemdictionaryQueryObject qo);

  boolean systemDictionaryIdDel(String id);

}
