package net.bodz.lily.schema.account.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.account.UserOtherIdType;

public class UserOtherIdTypeManager
        extends AbstractEntityManager<UserOtherIdType, UserOtherIdTypeMapper> {

    public UserOtherIdTypeManager(DataContext dataContext) {
        super(dataContext, UserOtherIdTypeMapper.class);
    }

}
