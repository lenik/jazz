package net.bodz.lily.schema.pub.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.pub.PostFav;

public class PostFavManager
        extends AbstractEntityManager<PostFav, PostFavMapper> {

    public PostFavManager(DataContext dataContext) {
        super(dataContext, PostFavMapper.class);
    }

}
