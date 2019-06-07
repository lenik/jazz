package net.bodz.lily.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.pub.PostTalkVote;
import net.bodz.lily.pub.PostTalkVoteSamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;

public class PostTalkVoteMapperTest
        extends AbstractMapperTest<PostTalkVote, PostTalkVoteMask, PostTalkVoteMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public PostTalkVote buildSample() {
        return PostTalkVoteSamples.build();
    }

}
