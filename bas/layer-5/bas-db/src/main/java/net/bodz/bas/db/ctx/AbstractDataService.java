package net.bodz.bas.db.ctx;

import net.bodz.bas.db.ibatis.IMapperProvider;
import net.bodz.bas.meta.decl.NotNull;

public abstract class AbstractDataService
        implements
            IDataContextAware {

    private DataContext dataContext;

    public AbstractDataService(DataContext dataContext) {
        this.dataContext = dataContext;
    }

    @NotNull
    @Override
    public DataContext getDataContext() {
        return dataContext;
    }

    @Override
    public void setDataContext(@NotNull DataContext dataContext) {
        this.dataContext = dataContext;
    }

    public IMapperProvider getMapperProvider() {
        return dataContext.getMapperProvider();
    }

}
