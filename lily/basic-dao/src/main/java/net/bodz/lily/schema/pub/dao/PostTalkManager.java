package net.bodz.lily.schema.pub.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.pub.PostTalk;

public class PostTalkManager
        extends AbstractEntityManager<PostTalk, PostTalkMapper> {

    public PostTalkManager(DataContext dataContext) {
        super(dataContext, PostTalkMapper.class);
    }

}
