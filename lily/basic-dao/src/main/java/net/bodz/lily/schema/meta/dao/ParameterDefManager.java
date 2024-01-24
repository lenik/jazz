package net.bodz.lily.schema.meta.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.meta.ParameterDef;

public class ParameterDefManager
        extends AbstractEntityManager<ParameterDef, ParameterDefMapper> {

    public ParameterDefManager(DataContext dataContext) {
        super(dataContext, ParameterDefMapper.class);
    }

}
