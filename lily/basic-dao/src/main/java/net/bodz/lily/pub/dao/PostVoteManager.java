package net.bodz.lily.pub.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.pub.PostVote;

public class PostVoteManager
        extends AbstractEntityManager<PostVote, PostVoteMapper> {

    public PostVoteManager(DataContext dataContext) {
        super(dataContext, PostVoteMapper.class);
    }

}
