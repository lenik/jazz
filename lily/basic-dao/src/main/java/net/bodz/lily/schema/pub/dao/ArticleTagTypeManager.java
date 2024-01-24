package net.bodz.lily.schema.pub.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.pub.ArticleTagType;

public class ArticleTagTypeManager
        extends AbstractEntityManager<ArticleTagType, ArticleTagTypeMapper> {

    public ArticleTagTypeManager(DataContext dataContext) {
        super(dataContext, ArticleTagTypeMapper.class);
    }

}
