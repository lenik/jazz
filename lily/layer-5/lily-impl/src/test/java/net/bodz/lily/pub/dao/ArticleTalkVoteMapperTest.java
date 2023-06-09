package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.ArticleTalkVote;
import net.bodz.lily.pub.ArticleTalkVoteSamples;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;

public class ArticleTalkVoteMapperTest
        extends AbstractTableTest<ArticleTalkVote, ArticleTalkVoteMask, ArticleTalkVoteMapper> {

    @Override
    public ArticleTalkVote buildSample()
            throws Exception {
        ArticleTalkVoteSamples a = new ArticleTalkVoteSamples();
        a.user = tables.pickAny(UserMapper.class, "user");
        a.parent = tables.pickAny(ArticleTalkMapper.class, "article_msg");
        return a.build();
    }

}
