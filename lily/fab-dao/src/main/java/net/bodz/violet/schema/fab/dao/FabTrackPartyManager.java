package net.bodz.violet.schema.fab.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.fab.FabTrackParty;

public class FabTrackPartyManager
        extends AbstractEntityManager<FabTrackParty, FabTrackPartyMapper> {

    public FabTrackPartyManager(DataContext dataContext) {
        super(dataContext, FabTrackPartyMapper.class);
    }

}
