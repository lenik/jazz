package net.bodz.lily.api.impl;

import net.bodz.bas.db.ibatis.IMapperTemplate;

import net.bodz.lily.api.AppCredit;
import net.bodz.lily.api.impl.AppCreditMask;

/**
 * @mapper.xml AppCreditMapper.xml
 */
public interface AppCreditMapper
        extends IMapperTemplate<AppCredit, AppCreditMask> {

}
