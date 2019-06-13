package net.bodz.lily.security.impl;

import net.bodz.bas.db.ibatis.IMapperTemplate;

import net.bodz.lily.security.UserCategory;
import net.bodz.lily.security.impl.UserCategoryMask;

/**
 * @mapper.xml UserCategoryMapper.xml
 */
public interface UserCategoryMapper
        extends IMapperTemplate<UserCategory, UserCategoryMask> {

}
