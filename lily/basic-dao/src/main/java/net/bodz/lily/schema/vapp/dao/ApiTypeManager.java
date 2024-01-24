package net.bodz.lily.schema.vapp.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.vapp.ApiType;

public class ApiTypeManager
        extends AbstractEntityManager<ApiType, ApiTypeMapper> {

    public ApiTypeManager(DataContext dataContext) {
        super(dataContext, ApiTypeMapper.class);
    }

}
