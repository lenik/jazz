package net.bodz.lily.contact.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.contact.Person;
import net.bodz.lily.dao.AbstractEntityManager;

public class PersonManager
        extends AbstractEntityManager<Person, PersonMapper> {

    public PersonManager(DataContext dataContext) {
        super(dataContext, PersonMapper.class);
    }

}
