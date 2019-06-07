package net.bodz.lily.api.impl;

import net.bodz.bas.db.ibatis.IMapperTemplate;

import net.bodz.lily.api.Api;
import net.bodz.lily.api.impl.ApiMask;

/**
 * @mapper.xml ApiMapper.xml
 */
public interface ApiMapper
        extends IMapperTemplate<Api, ApiMask> {

}
