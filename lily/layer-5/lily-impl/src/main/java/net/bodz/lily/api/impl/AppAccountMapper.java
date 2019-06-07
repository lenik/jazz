package net.bodz.lily.api.impl;

import net.bodz.bas.db.ibatis.IMapperTemplate;

import net.bodz.lily.api.AppAccount;
import net.bodz.lily.api.impl.AppAccountMask;

/**
 * @mapper.xml AppAccountMapper.xml
 */
public interface AppAccountMapper
        extends IMapperTemplate<AppAccount, AppAccountMask> {

}
