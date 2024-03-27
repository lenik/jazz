package net.bodz.lily.entity.manager;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.app.IDataApplication;
import net.bodz.lily.entity.type.IEntityTypeInfo;

public class EntityCommandContext
        implements
            IEntityCommandContext {

    IDataApplication dataApp;
    IEntityTypeInfo typeInfo;

    @Override
    public IDataApplication getDataApp() {
        return dataApp;
    }

    public void setDataApp(IDataApplication dataApp) {
        this.dataApp = dataApp;
    }

    @Override
    public DataContext getDataContext() {
        if (dataApp == null)
            return null;
        else
            return dataApp.getDataContext();
    }

    @Override
    public IEntityTypeInfo getEntityTypeInfo() {
        return typeInfo;
    }

    public void setTypeInfo(IEntityTypeInfo typeInfo) {
        this.typeInfo = typeInfo;
    }

}
