package net.bodz.bas.db.ctx;

import net.bodz.bas.db.ibatis.IMapperProvider;

public abstract class AbstractDataService
        implements
            IDataContextAware {

    private DataContext dataContext;

    public AbstractDataService(DataContext dataContext) {
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

    public IMapperProvider getMapperProvider() {
        return dataContext.getMapperProvider();
    }

}
