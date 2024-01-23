package net.bodz.lily.schema.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.PhaseDef;

public class PhaseDefManager
        extends AbstractEntityManager<PhaseDef, PhaseDefMapper> {

    public PhaseDefManager(DataContext dataContext) {
        super(dataContext, PhaseDefMapper.class);
    }

}
