package net.bodz.bas.db.ctx;

public interface IDataContextAware {

    DataContext getDataContext();

    void setDataContext(DataContext dataContext);

}
