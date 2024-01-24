package net.bodz.lily.schema.contact.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.contact.Organization;

public class OrganizationManager
        extends AbstractEntityManager<Organization, OrganizationMapper> {

    public OrganizationManager(DataContext dataContext) {
        super(dataContext, OrganizationMapper.class);
    }

}
