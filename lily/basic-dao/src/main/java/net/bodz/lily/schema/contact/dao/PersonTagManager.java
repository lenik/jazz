package net.bodz.lily.schema.contact.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.contact.PersonTag;

public class PersonTagManager
        extends AbstractEntityManager<PersonTag, PersonTagMapper> {

    public PersonTagManager(DataContext dataContext) {
        super(dataContext, PersonTagMapper.class);
    }

}
