package net.bodz.violet.tran.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.tran.TransportPhase;

public class TransportPhaseManager
        extends AbstractEntityManager<TransportPhase, TransportPhaseMapper> {

    public TransportPhaseManager(DataContext dataContext) {
        super(dataContext, TransportPhaseMapper.class);
    }

}
