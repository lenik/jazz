package net.bodz.lily.schema.pub.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.pub.PostBackref;

public class PostBackrefManager
        extends AbstractEntityManager<PostBackref, PostBackrefMapper> {

    public PostBackrefManager(DataContext dataContext) {
        super(dataContext, PostBackrefMapper.class);
    }

}
