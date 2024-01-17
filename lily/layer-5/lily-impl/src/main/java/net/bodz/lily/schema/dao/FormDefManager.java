package net.bodz.lily.schema.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.FormDef;

public class FormDefManager
        extends AbstractEntityManager<FormDef, FormDefMapper> {

    public FormDefManager(DataContext dataContext) {
        super(dataContext, FormDefMapper.class);
    }

}
