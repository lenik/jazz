package net.bodz.violet.art.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.art.ArtifactVote;
import net.bodz.violet.art.ArtifactVoteSamples;

public class ArtifactVoteManagerTest
        extends AbstractManagerTest<ArtifactVote, ArtifactVoteMapper, ArtifactVoteManager> {

    @Override
    public ArtifactVote buildSample()
            throws Exception {
        ArtifactVoteSamples a = new ArtifactVoteSamples();
        return a.buildWired(tables);
    }

}
