package net.bodz.lily.pub;

import net.bodz.lily.pub.dao.ArticleMapper;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class ArticleVoteSamples
        extends TestSampleBuilder {

    public Article parent;
    public User user;

    @Override
    public ArticleVote build()
            throws Exception {
        ArticleVote a = new ArticleVote();
        a.setParent(parent);
        a.setUser(user);
        a.setVoteScore(1607102641);
        return a;
    }

    @Override
    public ArticleVoteSamples wireAny(IRandomPicker picker) {
        this.parent = picker.pickAny(ArticleMapper.class, "article");
        this.user = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public ArticleVote buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
