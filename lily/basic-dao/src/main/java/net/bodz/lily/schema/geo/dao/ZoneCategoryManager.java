package net.bodz.lily.schema.geo.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.geo.ZoneCategory;

public class ZoneCategoryManager
        extends AbstractEntityManager<ZoneCategory, ZoneCategoryMapper> {

    public ZoneCategoryManager(DataContext dataContext) {
        super(dataContext, ZoneCategoryMapper.class);
    }

}
