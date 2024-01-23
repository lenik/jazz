package net.bodz.lily.contact.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.contact.Organization;
import net.bodz.lily.dao.AbstractEntityManager;

public class OrganizationManager
        extends AbstractEntityManager<Organization, OrganizationMapper> {

    public OrganizationManager(DataContext dataContext) {
        super(dataContext, OrganizationMapper.class);
    }

}
