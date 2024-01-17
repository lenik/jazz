package net.bodz.lily.pub.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.pub.PostBackref;

public class PostBackrefManager
        extends AbstractEntityManager<PostBackref, PostBackrefMapper> {

    public PostBackrefManager(DataContext dataContext) {
        super(dataContext, PostBackrefMapper.class);
    }

}
