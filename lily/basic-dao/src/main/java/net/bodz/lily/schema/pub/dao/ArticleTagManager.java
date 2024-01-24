package net.bodz.lily.schema.pub.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.pub.ArticleTag;

public class ArticleTagManager
        extends AbstractEntityManager<ArticleTag, ArticleTagMapper> {

    public ArticleTagManager(DataContext dataContext) {
        super(dataContext, ArticleTagMapper.class);
    }

}
