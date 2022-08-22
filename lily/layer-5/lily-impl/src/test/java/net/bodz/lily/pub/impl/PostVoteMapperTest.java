package net.bodz.lily.pub.impl;

import net.bodz.lily.pub.PostVote;
import net.bodz.lily.pub.PostVoteSamples;
import net.bodz.lily.test.AbstractTableTest;

public class PostVoteMapperTest
        extends AbstractTableTest<PostVote, PostVoteMask, PostVoteMapper> {

    @Override
    public PostVote buildSample() {
        return PostVoteSamples.build();
    }

}
