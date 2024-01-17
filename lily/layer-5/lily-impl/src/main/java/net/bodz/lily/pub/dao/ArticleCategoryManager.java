package net.bodz.lily.pub.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.pub.ArticleCategory;

public class ArticleCategoryManager
        extends AbstractEntityManager<ArticleCategory, ArticleCategoryMapper> {

    public ArticleCategoryManager(DataContext dataContext) {
        super(dataContext, ArticleCategoryMapper.class);
    }

}
