package net.bodz.lily.schema.meta.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.meta.PriorityDef;

public class PriorityDefManager
        extends AbstractEntityManager<PriorityDef, PriorityDefMapper> {

    public PriorityDefManager(DataContext dataContext) {
        super(dataContext, PriorityDefMapper.class);
    }

}
