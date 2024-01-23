package net.bodz.lily.pub.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.pub.PostTagType;

public class PostTagTypeManager
        extends AbstractEntityManager<PostTagType, PostTagTypeMapper> {

    public PostTagTypeManager(DataContext dataContext) {
        super(dataContext, PostTagTypeMapper.class);
    }

}
