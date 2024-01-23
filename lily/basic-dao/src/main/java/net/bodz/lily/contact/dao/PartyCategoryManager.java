package net.bodz.lily.contact.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.contact.PartyCategory;
import net.bodz.lily.dao.AbstractEntityManager;

public class PartyCategoryManager
        extends AbstractEntityManager<PartyCategory, PartyCategoryMapper> {

    public PartyCategoryManager(DataContext dataContext) {
        super(dataContext, PartyCategoryMapper.class);
    }

}
