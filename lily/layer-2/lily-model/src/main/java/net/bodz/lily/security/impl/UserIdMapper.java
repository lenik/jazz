package net.bodz.lily.security.impl;

import net.bodz.bas.db.ibatis.IMapperTemplate;

import net.bodz.lily.security.UserId;
import net.bodz.lily.security.impl.UserIdMask;

/**
 * @mapper.xml UserIdMapper.xml
 */
public interface UserIdMapper
        extends IMapperTemplate<UserId, UserIdMask> {

}
