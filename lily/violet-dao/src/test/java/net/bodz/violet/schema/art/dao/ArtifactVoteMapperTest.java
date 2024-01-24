package net.bodz.violet.schema.art.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.art.ArtifactVote;
import net.bodz.violet.schema.art.ArtifactVoteSamples;

public class ArtifactVoteMapperTest
        extends AbstractTableTest<ArtifactVote, ArtifactVoteMapper> {

    @Override
    public ArtifactVote buildSample()
            throws Exception {
        ArtifactVoteSamples a = new ArtifactVoteSamples();
        return a.buildWired(tables);
    }

}
