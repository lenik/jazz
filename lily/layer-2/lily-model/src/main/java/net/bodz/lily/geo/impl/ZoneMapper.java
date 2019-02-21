package net.bodz.lily.geo.impl;

import net.bodz.bas.db.ibatis.IMapperTemplate;

import net.bodz.lily.geo.Zone;
import net.bodz.lily.geo.impl.ZoneMask;

/**
 * @mapper.xml ZoneMapper.xml
 */
public interface ZoneMapper
        extends IMapperTemplate<Zone, ZoneMask> {

}
