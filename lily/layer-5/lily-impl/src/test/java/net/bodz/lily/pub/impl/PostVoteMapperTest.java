package net.bodz.lily.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.pub.PostVote;
import net.bodz.lily.pub.PostVoteSamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;

public class PostVoteMapperTest
        extends AbstractMapperTest<PostVote, PostVoteMask, PostVoteMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public PostVote buildSample() {
        return PostVoteSamples.build();
    }

}
