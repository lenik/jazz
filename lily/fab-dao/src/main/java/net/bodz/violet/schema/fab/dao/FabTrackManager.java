package net.bodz.violet.schema.fab.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.fab.FabTrack;

public class FabTrackManager
        extends AbstractEntityManager<FabTrack, FabTrackMapper> {

    public FabTrackManager(DataContext dataContext) {
        super(dataContext, FabTrackMapper.class);
    }

}
