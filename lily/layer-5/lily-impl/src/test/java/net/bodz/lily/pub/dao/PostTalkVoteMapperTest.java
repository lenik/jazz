package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.PostTalkVote;
import net.bodz.lily.pub.PostTalkVoteSamples;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;

public class PostTalkVoteMapperTest
        extends AbstractTableTest<PostTalkVote, PostTalkVoteMask, PostTalkVoteMapper> {

    @Override
    public PostTalkVote buildSample()
            throws Exception {
        PostTalkVoteSamples a = new PostTalkVoteSamples();
        a.user = tables.pickAny(UserMapper.class, "user");
        a.parent = tables.pickAny(PostTalkMapper.class, "post_msg");
        return a.build();
    }

}
