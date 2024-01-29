package net.bodz.lily.schema.contact.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.contact.PersonTagType;

public class PersonTagTypeManager
        extends AbstractEntityManager<PersonTagType, PersonTagTypeMapper> {

    public PersonTagTypeManager(DataContext dataContext) {
        super(dataContext, PersonTagTypeMapper.class);
    }

}
