package net.bodz.lily.security.login;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.security.impl.UserMapper;

public abstract class DataAccessLoginResolver
        implements ILoginResolver {

    protected final DataContext dataContext;
    protected UserMapper userMapper;

    public DataAccessLoginResolver(DataContext dataContext) {
        if (dataContext == null)
            throw new NullPointerException("dataContext");
        this.dataContext = dataContext;
        this.userMapper = dataContext.getMapper(UserMapper.class);
    }

}
