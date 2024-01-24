package net.bodz.lily.schema.account.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.account.UserOtherId;

public class UserOtherIdManager
        extends AbstractEntityManager<UserOtherId, UserOtherIdMapper> {

    public UserOtherIdManager(DataContext dataContext) {
        super(dataContext, UserOtherIdMapper.class);
    }

}
