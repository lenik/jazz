package net.bodz.violet.plan.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.plan.DiaryCategory;

public class DiaryCategoryManager
        extends AbstractEntityManager<DiaryCategory, DiaryCategoryMapper> {

    public DiaryCategoryManager(DataContext dataContext) {
        super(dataContext, DiaryCategoryMapper.class);
    }

}
