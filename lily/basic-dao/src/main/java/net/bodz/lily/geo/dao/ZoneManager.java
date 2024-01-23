package net.bodz.lily.geo.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.geo.Zone;

public class ZoneManager
        extends AbstractEntityManager<Zone, ZoneMapper> {

    public ZoneManager(DataContext dataContext) {
        super(dataContext, ZoneMapper.class);
    }

}
