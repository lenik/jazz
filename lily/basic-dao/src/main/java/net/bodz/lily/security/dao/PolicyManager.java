package net.bodz.lily.security.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.security.Policy;

public class PolicyManager
        extends AbstractEntityManager<Policy, PolicyMapper> {

    public PolicyManager(DataContext dataContext) {
        super(dataContext, PolicyMapper.class);
    }

}
