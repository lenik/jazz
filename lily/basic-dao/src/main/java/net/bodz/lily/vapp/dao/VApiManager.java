package net.bodz.lily.vapp.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.vapp.VApi;

public class VApiManager
        extends AbstractEntityManager<VApi, VApiMapper> {

    public VApiManager(DataContext dataContext) {
        super(dataContext, VApiMapper.class);
    }

}
