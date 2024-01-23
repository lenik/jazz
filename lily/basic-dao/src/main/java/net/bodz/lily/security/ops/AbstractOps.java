package net.bodz.lily.security.ops;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ctx.IDataContextAware;

public abstract class AbstractOps
        implements
            IDataContextAware {

    DataContext dataContext;

    public AbstractOps(DataContext dataContext) {
        if (dataContext == null)
            throw new NullPointerException("dataContext");
        this.dataContext = dataContext;
    }

    @Override
    public DataContext getDataContext() {
        return dataContext;
    }

    @Override
    public void setDataContext(DataContext dataContext) {
        this.dataContext = dataContext;
    }

}
