package net.bodz.lily.vapp.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.vapp.VAppCat;

public class VAppCatManager
        extends AbstractEntityManager<VAppCat, VAppCatMapper> {

    public VAppCatManager(DataContext dataContext) {
        super(dataContext, VAppCatMapper.class);
    }

}
