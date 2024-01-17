package net.bodz.lily.pub;

import net.bodz.lily.pub.dao.PostMapper;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class PostVoteSamples
        extends TestSampleBuilder {

    public Post parent;
    public User user;

    @Override
    public PostVote build()
            throws Exception {
        PostVote a = new PostVote();
        a.setParent(parent);
        a.setUser(user);
        a.setVoteScore(612466445);
        return a;
    }

    @Override
    public PostVoteSamples wireAny(IRandomPicker picker) {
        this.parent = picker.pickAny(PostMapper.class, "post");
        this.user = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public PostVote buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
