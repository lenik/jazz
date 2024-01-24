package net.bodz.lily.schema.vapp.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.vapp.VAppRequestApi;

public class VAppRequestApiManager
        extends AbstractEntityManager<VAppRequestApi, VAppRequestApiMapper> {

    public VAppRequestApiManager(DataContext dataContext) {
        super(dataContext, VAppRequestApiMapper.class);
    }

}
