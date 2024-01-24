package net.bodz.lily.schema.contact.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.contact.PartyCategory;

public class PartyCategoryManager
        extends AbstractEntityManager<PartyCategory, PartyCategoryMapper> {

    public PartyCategoryManager(DataContext dataContext) {
        super(dataContext, PartyCategoryMapper.class);
    }

}
