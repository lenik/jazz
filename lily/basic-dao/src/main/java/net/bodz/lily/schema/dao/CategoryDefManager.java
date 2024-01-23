package net.bodz.lily.schema.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.CategoryDef;

public class CategoryDefManager
        extends AbstractEntityManager<CategoryDef, CategoryDefMapper> {

    public CategoryDefManager(DataContext dataContext) {
        super(dataContext, CategoryDefMapper.class);
    }

}
