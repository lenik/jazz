package net.bodz.bas.db.test;

import org.junit.Assert;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ctx.DataHub;
import net.bodz.bas.db.ibatis.IMapper;

public abstract class DatabaseTester
        extends Assert {

    protected final DataContext dataContext;

    public DatabaseTester() {
        dataContext = DataHub.getPreferredHub().getTest();
    }

    public <T extends IMapper> T getMapper(Class<T> mapperClass) {
        return dataContext.getMapper(mapperClass);
    }

    public abstract void main()
            throws Exception;

}
