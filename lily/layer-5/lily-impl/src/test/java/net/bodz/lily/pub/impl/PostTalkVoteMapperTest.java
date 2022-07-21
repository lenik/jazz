package net.bodz.lily.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.pub.PostTalkVote;
import net.bodz.lily.pub.PostTalkVoteSamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;

public class PostTalkVoteMapperTest
        extends AbstractMapperTest<PostTalkVote, PostTalkVoteMask, PostTalkVoteMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public PostTalkVote buildSample() {
        return PostTalkVoteSamples.build();
    }

}
