package net.bodz.lily.schema.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.ParameterDef;

public class ParameterDefManager
        extends AbstractEntityManager<ParameterDef, ParameterDefMapper> {

    public ParameterDefManager(DataContext dataContext) {
        super(dataContext, ParameterDefMapper.class);
    }

}
