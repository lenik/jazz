package net.bodz.lily.schema.pub.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.pub.ArticleTalkVote;

public class ArticleTalkVoteManager
        extends AbstractEntityManager<ArticleTalkVote, ArticleTalkVoteMapper> {

    public ArticleTalkVoteManager(DataContext dataContext) {
        super(dataContext, ArticleTalkVoteMapper.class);
    }

}
