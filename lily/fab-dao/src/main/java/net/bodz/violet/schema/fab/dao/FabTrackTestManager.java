package net.bodz.violet.schema.fab.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.fab.FabTrackTest;

public class FabTrackTestManager
        extends AbstractEntityManager<FabTrackTest, FabTrackTestMapper> {

    public FabTrackTestManager(DataContext dataContext) {
        super(dataContext, FabTrackTestMapper.class);
    }

}
