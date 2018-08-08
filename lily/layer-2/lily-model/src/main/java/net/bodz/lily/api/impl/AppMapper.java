package net.bodz.lily.api.impl;

import net.bodz.bas.db.ibatis.IMapperTemplate;

import net.bodz.lily.api.App;
import net.bodz.lily.api.impl.AppMask;

/**
 * @mapper.xml AppMapper.xml
 */
public interface AppMapper
        extends IMapperTemplate<App, AppMask> {

}
