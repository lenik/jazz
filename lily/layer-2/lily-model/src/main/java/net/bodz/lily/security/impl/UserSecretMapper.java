package net.bodz.lily.security.impl;

import net.bodz.bas.db.ibatis.IMapperTemplate;
import net.bodz.lily.security.UserSecret;

/**
 * @mapper.xml UserSecretMapper.xml
 */
public interface UserSecretMapper
        extends IMapperTemplate<UserSecret, UserSecretMask> {

}
