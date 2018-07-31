package net.bodz.lily.model.base.security.impl;

import net.bodz.bas.db.ibatis.IMapperTemplate;

import net.bodz.lily.model.base.security.UserId;
import net.bodz.lily.model.base.security.impl.UserIdMask;

/**
 * @mapper.xml UserIdMapper.xml
 */
public interface UserIdMapper
        extends IMapperTemplate<UserId, UserIdMask> {

}
