package net.bodz.lily.schema.account.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.account.Policy;

public class PolicyManager
        extends AbstractEntityManager<Policy, PolicyMapper> {

    public PolicyManager(DataContext dataContext) {
        super(dataContext, PolicyMapper.class);
    }

}
