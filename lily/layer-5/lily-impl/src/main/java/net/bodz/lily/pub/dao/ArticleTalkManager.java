package net.bodz.lily.pub.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.pub.ArticleTalk;

public class ArticleTalkManager
        extends AbstractEntityManager<ArticleTalk, ArticleTalkMapper> {

    public ArticleTalkManager(DataContext dataContext) {
        super(dataContext, ArticleTalkMapper.class);
    }

}
