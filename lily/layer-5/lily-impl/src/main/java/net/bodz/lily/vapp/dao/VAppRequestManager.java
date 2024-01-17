package net.bodz.lily.vapp.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.vapp.VAppRequest;

public class VAppRequestManager
        extends AbstractEntityManager<VAppRequest, VAppRequestMapper> {

    public VAppRequestManager(DataContext dataContext) {
        super(dataContext, VAppRequestMapper.class);
    }

}
