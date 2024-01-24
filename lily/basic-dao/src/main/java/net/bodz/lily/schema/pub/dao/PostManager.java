package net.bodz.lily.schema.pub.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.pub.Post;

public class PostManager
        extends AbstractEntityManager<Post, PostMapper> {

    public PostManager(DataContext dataContext) {
        super(dataContext, PostMapper.class);
    }

}
