package net.bodz.violet.art.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.ArtifactVote;
import net.bodz.violet.art.ArtifactVoteSamples;

public class ArtifactVoteMapperTest
        extends AbstractTableTest<ArtifactVote, ArtifactVoteMapper> {

    @Override
    public ArtifactVote buildSample()
            throws Exception {
        ArtifactVoteSamples a = new ArtifactVoteSamples();
        return a.buildWired(tables);
    }

}
