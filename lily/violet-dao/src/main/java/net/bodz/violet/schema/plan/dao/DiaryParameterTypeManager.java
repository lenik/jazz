package net.bodz.violet.schema.plan.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.plan.DiaryParameterType;

public class DiaryParameterTypeManager
        extends AbstractEntityManager<DiaryParameterType, DiaryParameterTypeMapper> {

    public DiaryParameterTypeManager(DataContext dataContext) {
        super(dataContext, DiaryParameterTypeMapper.class);
    }

}
