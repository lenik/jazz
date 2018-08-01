package net.bodz.lily.security.impl;

import net.bodz.bas.db.ibatis.IMapperTemplate;

import net.bodz.lily.security.UserSecret;
import net.bodz.lily.security.impl.UserSecretMask;

/**
 * @mapper.xml UserSecretMapper.xml
 */
public interface UserSecretMapper
        extends IMapperTemplate<UserSecret, UserSecretMask> {

}
