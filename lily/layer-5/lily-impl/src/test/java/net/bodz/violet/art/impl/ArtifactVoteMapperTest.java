package net.bodz.violet.art.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.ArtifactVote;
import net.bodz.violet.art.ArtifactVoteSamples;

public class ArtifactVoteMapperTest
        extends AbstractTableTest<ArtifactVote, ArtifactVoteMask, ArtifactVoteMapper> {

    @Override
    public ArtifactVote buildSample() {
        return ArtifactVoteSamples.build();
    }

}
