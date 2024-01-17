package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.PostVote;
import net.bodz.lily.pub.PostVoteSamples;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;

public class PostVoteMapperTest
        extends AbstractTableTest<PostVote, PostVoteCriteriaBuilder, PostVoteMapper> {

    @Override
    public PostVote buildSample()
            throws Exception {
        PostVoteSamples a = new PostVoteSamples();
        a.parent = tables.pickAny(PostMapper.class, "post");
        a.user = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
