package net.bodz.lily.security.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.security.GroupType;

public class GroupTypeManager
        extends AbstractEntityManager<GroupType, GroupTypeMapper> {

    public GroupTypeManager(DataContext dataContext) {
        super(dataContext, GroupTypeMapper.class);
    }

}
