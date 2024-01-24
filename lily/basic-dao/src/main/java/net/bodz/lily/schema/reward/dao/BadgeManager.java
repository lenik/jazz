package net.bodz.lily.schema.reward.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.reward.Badge;

public class BadgeManager
        extends AbstractEntityManager<Badge, BadgeMapper> {

    public BadgeManager(DataContext dataContext) {
        super(dataContext, BadgeMapper.class);
    }

}
