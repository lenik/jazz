package net.bodz.violet.schema.plan.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.plan.Diary;

public class DiaryManager
        extends AbstractEntityManager<Diary, DiaryMapper> {

    public DiaryManager(DataContext dataContext) {
        super(dataContext, DiaryMapper.class);
    }

}
