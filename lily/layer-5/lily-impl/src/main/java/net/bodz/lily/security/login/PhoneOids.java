package net.bodz.lily.security.login;

import net.bodz.bas.db.ctx.AbstractDataContextAware;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.security.impl.UserMapper;
import net.bodz.lily.security.impl.UserOtherIdMapper;

public class PhoneOids
        extends AbstractDataContextAware {

    UserMapper userMapper;
    UserOtherIdMapper oidMapper;

    public PhoneOids(DataContext dataContext) {
        super(dataContext);
        userMapper = dataContext.getMapper(UserMapper.class);
        oidMapper = dataContext.getMapper(UserOtherIdMapper.class);
    }

}
