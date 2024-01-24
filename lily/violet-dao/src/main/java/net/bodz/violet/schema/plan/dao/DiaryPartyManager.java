package net.bodz.violet.schema.plan.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.plan.DiaryParty;

public class DiaryPartyManager
        extends AbstractEntityManager<DiaryParty, DiaryPartyMapper> {

    public DiaryPartyManager(DataContext dataContext) {
        super(dataContext, DiaryPartyMapper.class);
    }

}
