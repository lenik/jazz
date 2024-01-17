package net.bodz.lily.pub;

import net.bodz.lily.pub.dao.ArticleTalkMapper;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class ArticleTalkVoteSamples
        extends TestSampleBuilder {

    public User user;
    public ArticleTalk parent;

    @Override
    public ArticleTalkVote build()
            throws Exception {
        ArticleTalkVote a = new ArticleTalkVote();
        a.setUser(user);
        a.setParent(parent);
        a.setVoteScore(878694475);
        return a;
    }

    @Override
    public ArticleTalkVoteSamples wireAny(IRandomPicker picker) {
        this.user = picker.pickAny(UserMapper.class, "user");
        this.parent = picker.pickAny(ArticleTalkMapper.class, "article_msg");
        return this;
    }

    @Override
    public ArticleTalkVote buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
