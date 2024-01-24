package net.bodz.lily.schema.pub.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.pub.PostTag;

public class PostTagManager
        extends AbstractEntityManager<PostTag, PostTagMapper> {

    public PostTagManager(DataContext dataContext) {
        super(dataContext, PostTagMapper.class);
    }

}
