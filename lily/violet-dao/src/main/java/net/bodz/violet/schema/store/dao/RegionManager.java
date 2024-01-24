package net.bodz.violet.schema.store.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.store.Region;

public class RegionManager
        extends AbstractEntityManager<Region, RegionMapper> {

    public RegionManager(DataContext dataContext) {
        super(dataContext, RegionMapper.class);
    }

}
