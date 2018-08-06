package net.bodz.violet.pub.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.pub.PostVote;
import net.bodz.violet.pub.PostVoteSamples;

public class PostVoteMapperTest
        extends AbstractMapperTest<PostVote, PostVoteMask, PostVoteMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public PostVote buildSample() {
        return PostVoteSamples.build();
    }

}
