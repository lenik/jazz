package net.bodz.lily.schema.pub.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.pub.ArticleBackref;

public class ArticleBackrefManager
        extends AbstractEntityManager<ArticleBackref, ArticleBackrefMapper> {

    public ArticleBackrefManager(DataContext dataContext) {
        super(dataContext, ArticleBackrefMapper.class);
    }

}
