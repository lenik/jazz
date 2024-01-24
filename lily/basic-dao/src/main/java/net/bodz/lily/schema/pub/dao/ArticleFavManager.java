package net.bodz.lily.schema.pub.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.pub.ArticleFav;

public class ArticleFavManager
        extends AbstractEntityManager<ArticleFav, ArticleFavMapper> {

    public ArticleFavManager(DataContext dataContext) {
        super(dataContext, ArticleFavMapper.class);
    }

}
