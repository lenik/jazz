package net.bodz.violet.schema.art.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.art.ArtifactVote;
import net.bodz.violet.schema.art.ArtifactVoteSamples;

public class ArtifactVoteManagerTest
        extends AbstractManagerTest<ArtifactVote, ArtifactVoteMapper, ArtifactVoteManager> {

    @Override
    public ArtifactVote buildSample()
            throws Exception {
        ArtifactVoteSamples a = new ArtifactVoteSamples();
        return a.buildWired(tables);
    }

}
