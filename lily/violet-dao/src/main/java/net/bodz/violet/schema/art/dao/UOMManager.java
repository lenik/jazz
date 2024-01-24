package net.bodz.violet.schema.art.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.art.UOM;

public class UOMManager
        extends AbstractEntityManager<UOM, UOMMapper> {

    public UOMManager(DataContext dataContext) {
        super(dataContext, UOMMapper.class);
    }

}
