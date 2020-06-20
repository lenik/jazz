package net.bodz.bas.db.ctx;

public abstract class AbstractDataContextAware
        implements IDataContextAware {

    DataContext dataContext;

    public AbstractDataContextAware(DataContext dataContext) {
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
