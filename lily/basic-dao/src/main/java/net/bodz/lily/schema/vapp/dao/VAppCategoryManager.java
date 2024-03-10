package net.bodz.lily.schema.vapp.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.vapp.VAppCategory;

public class VAppCategoryManager
        extends AbstractEntityManager<VAppCategory, VAppCategoryMapper> {

    public VAppCategoryManager(DataContext dataContext) {
        super(dataContext, VAppCategoryMapper.class);
    }

}
