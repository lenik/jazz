package net.bodz.lily.schema.account.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.account.UserRun;

public class UserRunManager
        extends AbstractEntityManager<UserRun, UserRunMapper> {

    public UserRunManager(DataContext dataContext) {
        super(dataContext, UserRunMapper.class);
    }

}
