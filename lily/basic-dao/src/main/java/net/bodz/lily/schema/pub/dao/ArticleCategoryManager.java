package net.bodz.lily.schema.pub.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.pub.ArticleCategory;

public class ArticleCategoryManager
        extends AbstractEntityManager<ArticleCategory, ArticleCategoryMapper> {

    public ArticleCategoryManager(DataContext dataContext) {
        super(dataContext, ArticleCategoryMapper.class);
    }

}
