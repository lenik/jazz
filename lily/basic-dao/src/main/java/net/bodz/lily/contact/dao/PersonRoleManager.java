package net.bodz.lily.contact.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.contact.PersonRole;
import net.bodz.lily.dao.AbstractEntityManager;

public class PersonRoleManager
        extends AbstractEntityManager<PersonRole, PersonRoleMapper> {

    public PersonRoleManager(DataContext dataContext) {
        super(dataContext, PersonRoleMapper.class);
    }

}
