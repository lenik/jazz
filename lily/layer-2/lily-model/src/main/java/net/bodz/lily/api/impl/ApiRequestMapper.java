package net.bodz.lily.api.impl;

import net.bodz.bas.db.ibatis.IMapperTemplate;

import net.bodz.lily.api.ApiRequest;
import net.bodz.lily.api.impl.ApiRequestMask;

/**
 * @mapper.xml ApiRequestMapper.xml
 */
public interface ApiRequestMapper
        extends IMapperTemplate<ApiRequest, ApiRequestMask> {

}
