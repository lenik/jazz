package net.bodz.lily.schema.pub.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.pub.ArticleTalk;

public class ArticleTalkManager
        extends AbstractEntityManager<ArticleTalk, ArticleTalkMapper> {

    public ArticleTalkManager(DataContext dataContext) {
        super(dataContext, ArticleTalkMapper.class);
    }

}
