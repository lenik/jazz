package net.bodz.lily.model.base.security.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.bodz.bas.db.ibatis.IMapperTemplate;
import net.bodz.lily.model.base.security.User;

public interface UserMapper
        extends IMapperTemplate<User, UserMask> {

    List<User> findForLogin(@Param("m") UserMask mask);

}
