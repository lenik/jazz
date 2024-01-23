package net.bodz.violet.plan.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.plan.DiaryVote;

public class DiaryVoteManager
        extends AbstractEntityManager<DiaryVote, DiaryVoteMapper> {

    public DiaryVoteManager(DataContext dataContext) {
        super(dataContext, DiaryVoteMapper.class);
    }

}
