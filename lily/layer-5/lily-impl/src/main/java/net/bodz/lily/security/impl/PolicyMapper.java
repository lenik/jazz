package net.bodz.lily.security.impl;

import net.bodz.bas.db.ibatis.IEntityMapper;
import net.bodz.lily.security.Policy;

/**
 * @mapper.xml PolicyMapper.xml
 */
public interface PolicyMapper
        extends IEntityMapper<Policy, PolicyMask> {

}
