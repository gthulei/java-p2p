package com.hl.p2p.server;

import com.hl.p2p.pojo.Realauth;
import com.hl.p2p.query.PageResult;
import com.hl.p2p.query.RealauthQueryObject;

public interface IRealauthServer {

   void addRealauth(Realauth realauth);

   void updateRealauth(Realauth realauth);

   Realauth getRealauth(long id);

   Realauth getRealauthByid(long id);

   PageResult getRealauthPage(RealauthQueryObject qo);

   Realauth getRealauthApplier(Long applierId);
}
