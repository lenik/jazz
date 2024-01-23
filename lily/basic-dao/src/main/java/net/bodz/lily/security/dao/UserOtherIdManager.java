package net.bodz.lily.security.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.security.UserOtherId;

public class UserOtherIdManager
        extends AbstractEntityManager<UserOtherId, UserOtherIdMapper> {

    public UserOtherIdManager(DataContext dataContext) {
        super(dataContext, UserOtherIdMapper.class);
    }

}
