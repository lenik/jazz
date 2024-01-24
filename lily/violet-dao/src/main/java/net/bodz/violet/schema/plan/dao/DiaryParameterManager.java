package net.bodz.violet.schema.plan.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.plan.DiaryParameter;

public class DiaryParameterManager
        extends AbstractEntityManager<DiaryParameter, DiaryParameterMapper> {

    public DiaryParameterManager(DataContext dataContext) {
        super(dataContext, DiaryParameterMapper.class);
    }

}
