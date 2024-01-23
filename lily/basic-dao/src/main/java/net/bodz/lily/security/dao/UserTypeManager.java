package net.bodz.lily.security.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.security.UserType;

public class UserTypeManager
        extends AbstractEntityManager<UserType, UserTypeMapper> {

    public UserTypeManager(DataContext dataContext) {
        super(dataContext, UserTypeMapper.class);
    }

}
