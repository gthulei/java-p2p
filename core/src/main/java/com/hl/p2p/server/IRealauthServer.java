package com.hl.p2p.server;

import com.hl.p2p.pojo.Realauth;

public interface IRealauthServer {

   void addRealauth(Realauth realauth);

   void updateRealauth(Realauth realauth);

   Realauth getRealauth(long id);
}
