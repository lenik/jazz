package net.bodz.lily.model.base.security.impl;

import net.bodz.bas.db.ibatis.IMapperTemplate;

import net.bodz.lily.model.base.security.UserSecret;
import net.bodz.lily.model.base.security.impl.UserSecretMask;

/**
 * @mapper.xml UserSecretMapper.xml
 */
public interface UserSecretMapper
        extends IMapperTemplate<UserSecret, UserSecretMask> {

}
