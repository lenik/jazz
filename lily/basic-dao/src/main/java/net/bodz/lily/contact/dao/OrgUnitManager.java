package net.bodz.lily.contact.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.contact.OrgUnit;
import net.bodz.lily.dao.AbstractEntityManager;

public class OrgUnitManager
        extends AbstractEntityManager<OrgUnit, OrgUnitMapper> {

    public OrgUnitManager(DataContext dataContext) {
        super(dataContext, OrgUnitMapper.class);
    }

}
