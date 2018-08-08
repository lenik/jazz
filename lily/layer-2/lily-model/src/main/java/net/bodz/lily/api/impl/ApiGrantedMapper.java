package net.bodz.lily.api.impl;

import net.bodz.bas.db.ibatis.IMapperTemplate;

import net.bodz.lily.api.ApiGranted;
import net.bodz.lily.api.impl.ApiGrantedMask;

/**
 * @mapper.xml ApiGrantedMapper.xml
 */
public interface ApiGrantedMapper
        extends IMapperTemplate<ApiGranted, ApiGrantedMask> {

}
