package net.bodz.lily.schema.pub.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.pub.ArticleVote;

public class ArticleVoteManager
        extends AbstractEntityManager<ArticleVote, ArticleVoteMapper> {

    public ArticleVoteManager(DataContext dataContext) {
        super(dataContext, ArticleVoteMapper.class);
    }

}
