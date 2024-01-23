package net.bodz.lily.security.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.security.UserOtherIdType;

public class UserOtherIdTypeManager
        extends AbstractEntityManager<UserOtherIdType, UserOtherIdTypeMapper> {

    public UserOtherIdTypeManager(DataContext dataContext) {
        super(dataContext, UserOtherIdTypeMapper.class);
    }

}
