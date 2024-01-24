package net.bodz.lily.schema.pub;

import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.schema.pub.dao.PostTalkMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class PostTalkVoteSamples
        extends TestSampleBuilder {

    public User user;
    public PostTalk parent;

    @Override
    public PostTalkVote build()
            throws Exception {
        PostTalkVote a = new PostTalkVote();
        a.setUser(user);
        a.setParent(parent);
        a.setVoteScore(800046027);
        return a;
    }

    @Override
    public PostTalkVoteSamples wireAny(IRandomPicker picker) {
        this.user = picker.pickAny(UserMapper.class, "user");
        this.parent = picker.pickAny(PostTalkMapper.class, "post_msg");
        return this;
    }

    @Override
    public PostTalkVote buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
