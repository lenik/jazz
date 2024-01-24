package net.bodz.violet.schema.plan.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.plan.DiaryReview;

public class DiaryReviewManager
        extends AbstractEntityManager<DiaryReview, DiaryReviewMapper> {

    public DiaryReviewManager(DataContext dataContext) {
        super(dataContext, DiaryReviewMapper.class);
    }

}
