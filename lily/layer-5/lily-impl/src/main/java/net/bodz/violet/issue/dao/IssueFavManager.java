package net.bodz.violet.issue.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.issue.IssueFav;

public class IssueFavManager
        extends AbstractEntityManager<IssueFav, IssueFavMapper> {

    public IssueFavManager(DataContext dataContext) {
        super(dataContext, IssueFavMapper.class);
    }

}
