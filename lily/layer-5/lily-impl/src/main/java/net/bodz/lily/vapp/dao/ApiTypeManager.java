package net.bodz.lily.vapp.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.vapp.ApiType;

public class ApiTypeManager
        extends AbstractEntityManager<ApiType, ApiTypeMapper> {

    public ApiTypeManager(DataContext dataContext) {
        super(dataContext, ApiTypeMapper.class);
    }

}
