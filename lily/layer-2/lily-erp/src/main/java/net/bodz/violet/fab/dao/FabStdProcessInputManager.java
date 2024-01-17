package net.bodz.violet.fab.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.fab.FabStdProcessInput;

public class FabStdProcessInputManager
        extends AbstractEntityManager<FabStdProcessInput, FabStdProcessInputMapper> {

    public FabStdProcessInputManager(DataContext dataContext) {
        super(dataContext, FabStdProcessInputMapper.class);
    }

}
