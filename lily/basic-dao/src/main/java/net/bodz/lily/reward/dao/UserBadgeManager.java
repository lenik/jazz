package net.bodz.lily.reward.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.reward.UserBadge;

public class UserBadgeManager
        extends AbstractEntityManager<UserBadge, UserBadgeMapper> {

    public UserBadgeManager(DataContext dataContext) {
        super(dataContext, UserBadgeMapper.class);
    }

}
