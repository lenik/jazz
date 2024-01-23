package net.bodz.lily.entity.manager;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.ParseException;
import net.bodz.lily.app.IDataApplication;
import net.bodz.lily.entity.IdFn;
import net.bodz.lily.entity.type.IEntityTypeInfo;

public class EntityCommandContext
        implements
            IEntityCommandContext {

    IDataApplication dataApp;

    IEntityTypeInfo typeInfo;

    ResolvedEntity resolvedEntity;

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
    public IEntityTypeInfo getTypeInfo() {
        return typeInfo;
    }

    public void setTypeInfo(IEntityTypeInfo typeInfo) {
        this.typeInfo = typeInfo;
    }

    @Override
    public ResolvedEntity getResolvedEntity() {
        return resolvedEntity;
    }

    public void setResolvedEntity(ResolvedEntity resolvedEntity) {
        this.resolvedEntity = resolvedEntity;
    }

    @Override
    public Object parseId(String idStr)
            throws ParseException {
        if (idStr == null) {
            return null;
        }

        Class<?> idType = typeInfo.getIdClass();
        if (idType == null)
            throw new IllegalUsageException("not id-capable: " + typeInfo.getEntityClass());

        Object id;
        try {
            id = IdFn.parseId(idType, idStr);
        } catch (ParseException e) {
            throw new ParseException(String.format(//
                    "error parse id \"%s\"): %s", idStr, e.getMessage()), e);
        }

        return id;
    }

}
