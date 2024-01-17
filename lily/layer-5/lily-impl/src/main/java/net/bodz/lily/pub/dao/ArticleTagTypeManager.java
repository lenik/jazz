package net.bodz.lily.pub.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.pub.ArticleTagType;

public class ArticleTagTypeManager
        extends AbstractEntityManager<ArticleTagType, ArticleTagTypeMapper> {

    public ArticleTagTypeManager(DataContext dataContext) {
        super(dataContext, ArticleTagTypeMapper.class);
    }

}
