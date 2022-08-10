package net.bodz.violet.art.impl;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.art.ArtifactVote;
import net.bodz.violet.art.ArtifactVoteSamples;

public class ArtifactVoteMapperTest
        extends AbstractMapperTest<ArtifactVote, ArtifactVoteMask, ArtifactVoteMapper> {

    @Override
    public ArtifactVote buildSample() {
        return ArtifactVoteSamples.build();
    }

}
