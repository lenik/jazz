package net.bodz.lily.geo.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.geo.ZoneCategory;

public class ZoneCategoryManager
        extends AbstractEntityManager<ZoneCategory, ZoneCategoryMapper> {

    public ZoneCategoryManager(DataContext dataContext) {
        super(dataContext, ZoneCategoryMapper.class);
    }

}
