package net.bodz.lily.schema.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.FormParameter;

public class FormParameterManager
        extends AbstractEntityManager<FormParameter, FormParameterMapper> {

    public FormParameterManager(DataContext dataContext) {
        super(dataContext, FormParameterMapper.class);
    }

}
