package net.bodz.violet.fab.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.fab.FabProcessSerial;

public class FabProcessSerialManager
        extends AbstractEntityManager<FabProcessSerial, FabProcessSerialMapper> {

    public FabProcessSerialManager(DataContext dataContext) {
        super(dataContext, FabProcessSerialMapper.class);
    }

}
