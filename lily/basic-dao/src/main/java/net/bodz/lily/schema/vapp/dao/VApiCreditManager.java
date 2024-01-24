package net.bodz.lily.schema.vapp.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.vapp.VApiCredit;

public class VApiCreditManager
        extends AbstractEntityManager<VApiCredit, VApiCreditMapper> {

    public VApiCreditManager(DataContext dataContext) {
        super(dataContext, VApiCreditMapper.class);
    }

}
