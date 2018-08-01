package net.bodz.lily.security.impl;

import net.bodz.bas.db.ibatis.IMapperTemplate;

import net.bodz.lily.security.Policy;
import net.bodz.lily.security.impl.PolicyMask;

/**
 * @mapper.xml PolicyMapper.xml
 */
public interface PolicyMapper
        extends IMapperTemplate<Policy, PolicyMask> {

}
