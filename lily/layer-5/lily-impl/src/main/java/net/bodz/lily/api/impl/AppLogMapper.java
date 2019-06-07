package net.bodz.lily.api.impl;

import net.bodz.bas.db.ibatis.IMapperTemplate;

import net.bodz.lily.api.AppLog;
import net.bodz.lily.api.impl.AppLogMask;

/**
 * @mapper.xml AppLogMapper.xml
 */
public interface AppLogMapper
        extends IMapperTemplate<AppLog, AppLogMask> {

}
