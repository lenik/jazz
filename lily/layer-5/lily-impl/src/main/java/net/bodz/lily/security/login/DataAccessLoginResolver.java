package net.bodz.lily.security.login;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.security.impl.UserMapper;
import net.bodz.lily.security.impl.UserOtherIdMapper;
import net.bodz.lily.security.impl.UserSecretMapper;

public abstract class DataAccessLoginResolver
        extends AbstractLoginResolver {

    protected final DataContext dataContext;
    protected UserMapper userMapper;
    protected UserSecretMapper userSecretMapper;
    protected UserOtherIdMapper userOtherIdMapper;

    public DataAccessLoginResolver(DataContext dataContext) {
        if (dataContext == null)
            throw new NullPointerException("dataContext");
        this.dataContext = dataContext;
        this.userMapper = dataContext.getMapper(UserMapper.class);
        this.userSecretMapper = dataContext.getMapper(UserSecretMapper.class);
        this.userOtherIdMapper = dataContext.getMapper(UserOtherIdMapper.class);
    }

}
