package net.bodz.lily.schema.vapp.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.vapp.VAppRequest;

public class VAppRequestManager
        extends AbstractEntityManager<VAppRequest, VAppRequestMapper> {

    public VAppRequestManager(DataContext dataContext) {
        super(dataContext, VAppRequestMapper.class);
    }

}
