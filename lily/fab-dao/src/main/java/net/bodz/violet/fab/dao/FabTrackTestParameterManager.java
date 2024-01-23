package net.bodz.violet.fab.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.fab.FabTrackTestParameter;

public class FabTrackTestParameterManager
        extends AbstractEntityManager<FabTrackTestParameter, FabTrackTestParameterMapper> {

    public FabTrackTestParameterManager(DataContext dataContext) {
        super(dataContext, FabTrackTestParameterMapper.class);
    }

}
