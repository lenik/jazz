package net.bodz.lily.vapp.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.vapp.VApp;

public class VAppManager
        extends AbstractEntityManager<VApp, VAppMapper> {

    public VAppManager(DataContext dataContext) {
        super(dataContext, VAppMapper.class);
    }

}
