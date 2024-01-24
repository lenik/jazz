package net.bodz.lily.schema.pub.dao;

import net.bodz.lily.schema.pub.PostTalkVote;
import net.bodz.lily.schema.pub.PostTalkVoteSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class PostTalkVoteManagerTest
        extends AbstractManagerTest<PostTalkVote, PostTalkVoteMapper, PostTalkVoteManager> {

    @Override
    public PostTalkVote buildSample()
            throws Exception {
        PostTalkVoteSamples a = new PostTalkVoteSamples();
        return a.buildWired(tables);
    }

}
