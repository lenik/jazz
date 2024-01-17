package net.bodz.lily.pub.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.pub.ArticleParameterType;

public class ArticleParameterTypeManager
        extends AbstractEntityManager<ArticleParameterType, ArticleParameterTypeMapper> {

    public ArticleParameterTypeManager(DataContext dataContext) {
        super(dataContext, ArticleParameterTypeMapper.class);
    }

}
