package net.bodz.violet.schema.fab.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.fab.FabStdTestParameter;

public class FabStdTestParameterManager
        extends AbstractEntityManager<FabStdTestParameter, FabStdTestParameterMapper> {

    public FabStdTestParameterManager(DataContext dataContext) {
        super(dataContext, FabStdTestParameterMapper.class);
    }

}
