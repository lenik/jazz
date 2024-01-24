package net.bodz.lily.schema.contact.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.contact.PersonRole;

public class PersonRoleManager
        extends AbstractEntityManager<PersonRole, PersonRoleMapper> {

    public PersonRoleManager(DataContext dataContext) {
        super(dataContext, PersonRoleMapper.class);
    }

}
