package net.bodz.violet.schema.plan.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.plan.DiaryVote;

public class DiaryVoteManager
        extends AbstractEntityManager<DiaryVote, DiaryVoteMapper> {

    public DiaryVoteManager(DataContext dataContext) {
        super(dataContext, DiaryVoteMapper.class);
    }

}
