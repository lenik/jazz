package net.bodz.lily.schema.pub.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.pub.PostTagType;

public class PostTagTypeManager
        extends AbstractEntityManager<PostTagType, PostTagTypeMapper> {

    public PostTagTypeManager(DataContext dataContext) {
        super(dataContext, PostTagTypeMapper.class);
    }

}
