package net.bodz.lily.schema.util.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.util.UomRow;

public class UomRowManager
        extends AbstractEntityManager<UomRow, UomRowMapper> {

    public UomRowManager(DataContext dataContext) {
        super(dataContext, UomRowMapper.class);
    }

}
