package net.bodz.lily.schema.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.ParameterValue;

public class ParameterValueManager
        extends AbstractEntityManager<ParameterValue, ParameterValueMapper> {

    public ParameterValueManager(DataContext dataContext) {
        super(dataContext, ParameterValueMapper.class);
    }

}
