package net.bodz.lily.schema.meta.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.meta.FormParameter;

public class FormParameterManager
        extends AbstractEntityManager<FormParameter, FormParameterMapper> {

    public FormParameterManager(DataContext dataContext) {
        super(dataContext, FormParameterMapper.class);
    }

}
