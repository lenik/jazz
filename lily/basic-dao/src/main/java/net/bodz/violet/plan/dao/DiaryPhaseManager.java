package net.bodz.violet.plan.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.plan.DiaryPhase;

public class DiaryPhaseManager
        extends AbstractEntityManager<DiaryPhase, DiaryPhaseMapper> {

    public DiaryPhaseManager(DataContext dataContext) {
        super(dataContext, DiaryPhaseMapper.class);
    }

}
