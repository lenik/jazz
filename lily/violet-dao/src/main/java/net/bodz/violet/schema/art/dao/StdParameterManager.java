package net.bodz.violet.schema.art.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.art.StdParameter;

public class StdParameterManager
        extends AbstractEntityManager<StdParameter, StdParameterMapper> {

    public StdParameterManager(DataContext dataContext) {
        super(dataContext, StdParameterMapper.class);
    }

}
