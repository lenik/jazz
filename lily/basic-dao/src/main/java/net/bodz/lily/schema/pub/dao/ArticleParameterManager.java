package net.bodz.lily.schema.pub.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.pub.ArticleParameter;

public class ArticleParameterManager
        extends AbstractEntityManager<ArticleParameter, ArticleParameterMapper> {

    public ArticleParameterManager(DataContext dataContext) {
        super(dataContext, ArticleParameterMapper.class);
    }

}
