package net.bodz.lily.schema.account.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.account.User;

public class UserManager
        extends AbstractEntityManager<User, UserMapper> {

    public UserManager(DataContext dataContext) {
        super(dataContext, UserMapper.class);
    }

}
