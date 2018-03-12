package net.bodz.bas.db.test;

import org.junit.Assert;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ctx.JdbcDataContexts;
import net.bodz.bas.db.ibatis.IMapper;
import net.bodz.bas.db.jdbc.ConnectOptions;

public abstract class DatabaseTester
        extends Assert {

    protected ConnectOptions connOpts = new ConnectOptions();
    protected final DataContext dataContext;

    public DatabaseTester() {
        config(connOpts);
        dataContext = JdbcDataContexts.getInstance().get(connOpts);
    }

    protected void config(ConnectOptions connOpts) {
        connOpts.setHostName("localhost");
        connOpts.setPort(5432);
        connOpts.setDatabase("postgres");
        connOpts.setUserName("postgres");
        connOpts.setPassword("cW3EADp8");
    }

    public <T extends IMapper> T getMapper(Class<T> mapperClass) {
        return dataContext.getMapper(mapperClass);
    }

    public abstract void main()
            throws Exception;

}
