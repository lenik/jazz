package net.bodz.violet.store.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.store.Region;

public class RegionManager
        extends AbstractEntityManager<Region, RegionMapper> {

    public RegionManager(DataContext dataContext) {
        super(dataContext, RegionMapper.class);
    }

}
