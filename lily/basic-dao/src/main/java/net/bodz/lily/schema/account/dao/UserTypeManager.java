package net.bodz.lily.schema.account.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.account.UserType;

public class UserTypeManager
        extends AbstractEntityManager<UserType, UserTypeMapper> {

    public UserTypeManager(DataContext dataContext) {
        super(dataContext, UserTypeMapper.class);
    }

}
