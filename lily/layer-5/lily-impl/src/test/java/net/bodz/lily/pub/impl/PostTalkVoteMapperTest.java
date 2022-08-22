package net.bodz.lily.pub.impl;

import net.bodz.lily.pub.PostTalkVote;
import net.bodz.lily.pub.PostTalkVoteSamples;
import net.bodz.lily.test.AbstractTableTest;

public class PostTalkVoteMapperTest
        extends AbstractTableTest<PostTalkVote, PostTalkVoteMask, PostTalkVoteMapper> {

    @Override
    public PostTalkVote buildSample() {
        return PostTalkVoteSamples.build();
    }

}
