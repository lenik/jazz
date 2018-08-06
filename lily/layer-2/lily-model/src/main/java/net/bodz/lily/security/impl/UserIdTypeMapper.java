package net.bodz.lily.security.impl;

import net.bodz.bas.db.ibatis.IMapperTemplate;

import net.bodz.lily.security.UserIdType;
import net.bodz.lily.security.impl.UserIdTypeMask;

/**
 * @mapper.xml UserIdTypeMapper.xml
 */
public interface UserIdTypeMapper
        extends IMapperTemplate<UserIdType, UserIdTypeMask> {

}
