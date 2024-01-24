package net.bodz.lily.schema.contact.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.contact.OrgUnit;

public class OrgUnitManager
        extends AbstractEntityManager<OrgUnit, OrgUnitMapper> {

    public OrgUnitManager(DataContext dataContext) {
        super(dataContext, OrgUnitMapper.class);
    }

}
