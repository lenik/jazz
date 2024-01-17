package net.bodz.violet.plan.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.plan.DiaryReviewVote;

public class DiaryReviewVoteManager
        extends AbstractEntityManager<DiaryReviewVote, DiaryReviewVoteMapper> {

    public DiaryReviewVoteManager(DataContext dataContext) {
        super(dataContext, DiaryReviewVoteMapper.class);
    }

}
