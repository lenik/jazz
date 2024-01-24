package net.bodz.violet.schema.fab.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.fab.FabProcessSerial;

public class FabProcessSerialManager
        extends AbstractEntityManager<FabProcessSerial, FabProcessSerialMapper> {

    public FabProcessSerialManager(DataContext dataContext) {
        super(dataContext, FabProcessSerialMapper.class);
    }

}
