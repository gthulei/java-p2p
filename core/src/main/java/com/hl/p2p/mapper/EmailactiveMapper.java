package com.hl.p2p.mapper;

import com.hl.p2p.pojo.Emailactive;
import java.util.List;

public interface EmailactiveMapper {

    int insert(Emailactive record);

    Emailactive selectByUuid(String uuid);

    int updateByUuid(Emailactive record);
}