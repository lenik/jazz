package net.bodz.lily.pub.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.pub.Article;

public class ArticleManager
        extends AbstractEntityManager<Article, ArticleMapper> {

    public ArticleManager(DataContext dataContext) {
        super(dataContext, ArticleMapper.class);
    }

}
