package net.bodz.violet.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.pub.PostTalkVote;
import net.bodz.violet.pub.PostTalkVoteSamples;

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
