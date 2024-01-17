package net.bodz.lily.vapp.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.vapp.VApiCredit;

public class VApiCreditManager
        extends AbstractEntityManager<VApiCredit, VApiCreditMapper> {

    public VApiCreditManager(DataContext dataContext) {
        super(dataContext, VApiCreditMapper.class);
    }

}
