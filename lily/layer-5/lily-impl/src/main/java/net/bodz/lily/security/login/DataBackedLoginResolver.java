package net.bodz.lily.security.login;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.security.impl.UserMapper;

public abstract class DataBackedLoginResolver
        extends AbstractLoginResolver {

    protected final DataContext dataContext;
    protected UserMapper userMapper;

    public DataBackedLoginResolver(DataContext dataContext) {
        if (dataContext == null)
            throw new NullPointerException("dataContext");
        this.dataContext = dataContext;
        this.userMapper = dataContext.getMapper(UserMapper.class);
    }

}
