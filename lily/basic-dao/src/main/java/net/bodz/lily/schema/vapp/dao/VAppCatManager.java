package net.bodz.lily.schema.vapp.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.vapp.VAppCat;

public class VAppCatManager
        extends AbstractEntityManager<VAppCat, VAppCatMapper> {

    public VAppCatManager(DataContext dataContext) {
        super(dataContext, VAppCatMapper.class);
    }

}
