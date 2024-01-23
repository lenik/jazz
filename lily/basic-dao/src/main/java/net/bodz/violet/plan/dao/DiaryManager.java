package net.bodz.violet.plan.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.plan.Diary;

public class DiaryManager
        extends AbstractEntityManager<Diary, DiaryMapper> {

    public DiaryManager(DataContext dataContext) {
        super(dataContext, DiaryMapper.class);
    }

}
