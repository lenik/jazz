package net.bodz.violet.schema.tran.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.tran.TransportPhase;

public class TransportPhaseManager
        extends AbstractEntityManager<TransportPhase, TransportPhaseMapper> {

    public TransportPhaseManager(DataContext dataContext) {
        super(dataContext, TransportPhaseMapper.class);
    }

}
