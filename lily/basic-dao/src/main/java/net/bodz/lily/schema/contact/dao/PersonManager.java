package net.bodz.lily.schema.contact.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.contact.Person;

public class PersonManager
        extends AbstractEntityManager<Person, PersonMapper> {

    public PersonManager(DataContext dataContext) {
        super(dataContext, PersonMapper.class);
    }

}
