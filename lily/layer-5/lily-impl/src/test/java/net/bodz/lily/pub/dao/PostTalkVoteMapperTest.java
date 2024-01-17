package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.PostTalkVote;
import net.bodz.lily.pub.PostTalkVoteSamples;
import net.bodz.lily.test.AbstractTableTest;

public class PostTalkVoteMapperTest
        extends AbstractTableTest<PostTalkVote, PostTalkVoteMapper> {

    @Override
    public PostTalkVote buildSample()
            throws Exception {
        PostTalkVoteSamples a = new PostTalkVoteSamples();
        return a.buildWired(tables);
    }

}
