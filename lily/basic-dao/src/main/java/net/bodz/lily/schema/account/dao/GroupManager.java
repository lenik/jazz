package net.bodz.lily.schema.account.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.account.Group;

public class GroupManager
        extends AbstractEntityManager<Group, GroupMapper> {

    public GroupManager(DataContext dataContext) {
        super(dataContext, GroupMapper.class);
    }

}
