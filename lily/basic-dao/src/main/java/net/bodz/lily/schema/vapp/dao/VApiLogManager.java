package net.bodz.lily.schema.vapp.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.vapp.VApiLog;

public class VApiLogManager
        extends AbstractEntityManager<VApiLog, VApiLogMapper> {

    public VApiLogManager(DataContext dataContext) {
        super(dataContext, VApiLogMapper.class);
    }

}
