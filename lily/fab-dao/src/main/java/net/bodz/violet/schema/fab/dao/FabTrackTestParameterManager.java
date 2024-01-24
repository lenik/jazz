package net.bodz.violet.schema.fab.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.fab.FabTrackTestParameter;

public class FabTrackTestParameterManager
        extends AbstractEntityManager<FabTrackTestParameter, FabTrackTestParameterMapper> {

    public FabTrackTestParameterManager(DataContext dataContext) {
        super(dataContext, FabTrackTestParameterMapper.class);
    }

}
