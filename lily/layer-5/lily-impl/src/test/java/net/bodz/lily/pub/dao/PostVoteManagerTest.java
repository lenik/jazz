package net.bodz.lily.pub.dao;

import net.bodz.lily.pub.PostVote;
import net.bodz.lily.pub.PostVoteSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class PostVoteManagerTest
        extends AbstractManagerTest<PostVote, PostVoteMapper, PostVoteManager> {

    @Override
    public PostVote buildSample()
            throws Exception {
        PostVoteSamples a = new PostVoteSamples();
        return a.buildWired(tables);
    }

}
