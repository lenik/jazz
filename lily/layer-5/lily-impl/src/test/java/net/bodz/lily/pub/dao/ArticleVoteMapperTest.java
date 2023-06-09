package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.ArticleVote;
import net.bodz.lily.pub.ArticleVoteSamples;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;

public class ArticleVoteMapperTest
        extends AbstractTableTest<ArticleVote, ArticleVoteMask, ArticleVoteMapper> {

    @Override
    public ArticleVote buildSample()
            throws Exception {
        ArticleVoteSamples a = new ArticleVoteSamples();
        a.parent = tables.pickAny(ArticleMapper.class, "article");
        a.user = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
