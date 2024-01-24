package net.bodz.lily.schema.pub.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.pub.ArticleParameterType;

public class ArticleParameterTypeManager
        extends AbstractEntityManager<ArticleParameterType, ArticleParameterTypeMapper> {

    public ArticleParameterTypeManager(DataContext dataContext) {
        super(dataContext, ArticleParameterTypeMapper.class);
    }

}
