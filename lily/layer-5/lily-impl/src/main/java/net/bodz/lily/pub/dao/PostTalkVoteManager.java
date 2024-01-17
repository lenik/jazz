package net.bodz.lily.pub.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.pub.PostTalkVote;

public class PostTalkVoteManager
        extends AbstractEntityManager<PostTalkVote, PostTalkVoteMapper> {

    public PostTalkVoteManager(DataContext dataContext) {
        super(dataContext, PostTalkVoteMapper.class);
    }

}
