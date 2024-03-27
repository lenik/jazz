package net.bodz.lily.entity.manager;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ibatis.sql.SelectOptions;
import net.bodz.lily.app.IDataApplication;
import net.bodz.lily.entity.type.IEntityTypeInfo;

public interface IEntityCommandContext {

    IDataApplication getDataApp();

    DataContext getDataContext();

    IEntityTypeInfo getEntityTypeInfo();

    default SelectOptions newSelectOptions() {
        return new SelectOptions();
    }

}
