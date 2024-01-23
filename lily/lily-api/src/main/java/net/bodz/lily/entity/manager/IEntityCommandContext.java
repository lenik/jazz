package net.bodz.lily.entity.manager;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.err.ParseException;
import net.bodz.lily.app.IDataApplication;
import net.bodz.lily.entity.type.IEntityTypeInfo;

public interface IEntityCommandContext {

    IDataApplication getDataApp();

    DataContext getDataContext();

    IEntityTypeInfo getTypeInfo();

    ResolvedEntity getResolvedEntity();

    Object parseId(String idStr)
            throws ParseException;

}
