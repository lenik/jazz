package net.bodz.violet.schema.store.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.store.RegionLevel;

public class RegionLevelManager
        extends AbstractEntityManager<RegionLevel, RegionLevelMapper> {

    public RegionLevelManager(DataContext dataContext) {
        super(dataContext, RegionLevelMapper.class);
    }

}
