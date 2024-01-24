package net.bodz.violet.schema.plan.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.plan.DiaryTag;

public class DiaryTagManager
        extends AbstractEntityManager<DiaryTag, DiaryTagMapper> {

    public DiaryTagManager(DataContext dataContext) {
        super(dataContext, DiaryTagMapper.class);
    }

}
