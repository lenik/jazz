package net.bodz.lily.schema.pub.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.pub.PostCategory;

public class PostCategoryManager
        extends AbstractEntityManager<PostCategory, PostCategoryMapper> {

    public PostCategoryManager(DataContext dataContext) {
        super(dataContext, PostCategoryMapper.class);
    }

}
