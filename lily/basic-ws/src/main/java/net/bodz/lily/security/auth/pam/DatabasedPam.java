package net.bodz.lily.security.auth.pam;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.schema.account.dao.UserMapper;

public abstract class DatabasedPam
        extends AbstractPam {

    protected final DataContext dataContext;
    protected UserMapper userMapper;

    public DatabasedPam(DataContext dataContext) {
        if (dataContext == null)
            throw new NullPointerException("dataContext");
        this.dataContext = dataContext;
        this.userMapper = dataContext.getMapper(UserMapper.class);
    }

    protected DataContext getDataContext() {
        return dataContext;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "@" + dataContext.getId();
    }

}
