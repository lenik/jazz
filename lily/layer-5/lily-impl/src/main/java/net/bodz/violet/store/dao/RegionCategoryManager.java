package net.bodz.violet.store.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.store.RegionCategory;

public class RegionCategoryManager
        extends AbstractEntityManager<RegionCategory, RegionCategoryMapper> {

    public RegionCategoryManager(DataContext dataContext) {
        super(dataContext, RegionCategoryMapper.class);
    }

}
