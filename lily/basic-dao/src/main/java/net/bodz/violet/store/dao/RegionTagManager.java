package net.bodz.violet.store.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.store.RegionTag;

public class RegionTagManager
        extends AbstractEntityManager<RegionTag, RegionTagMapper> {

    public RegionTagManager(DataContext dataContext) {
        super(dataContext, RegionTagMapper.class);
    }

}
