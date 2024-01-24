package net.bodz.lily.schema.util.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.util.Uom;

public class UomManager
        extends AbstractEntityManager<Uom, UomMapper> {

    public UomManager(DataContext dataContext) {
        super(dataContext, UomMapper.class);
    }

}
