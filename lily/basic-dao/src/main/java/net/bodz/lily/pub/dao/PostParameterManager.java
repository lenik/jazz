package net.bodz.lily.pub.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.pub.PostParameter;

public class PostParameterManager
        extends AbstractEntityManager<PostParameter, PostParameterMapper> {

    public PostParameterManager(DataContext dataContext) {
        super(dataContext, PostParameterMapper.class);
    }

}
