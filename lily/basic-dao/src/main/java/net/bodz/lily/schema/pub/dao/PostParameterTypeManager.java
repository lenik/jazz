package net.bodz.lily.schema.pub.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.pub.PostParameterType;

public class PostParameterTypeManager
        extends AbstractEntityManager<PostParameterType, PostParameterTypeMapper> {

    public PostParameterTypeManager(DataContext dataContext) {
        super(dataContext, PostParameterTypeMapper.class);
    }

}
