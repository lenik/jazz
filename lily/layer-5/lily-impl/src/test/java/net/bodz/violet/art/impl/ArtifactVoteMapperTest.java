package net.bodz.violet.art.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.art.ArtifactVote;
import net.bodz.violet.art.ArtifactVoteSamples;

public class ArtifactVoteMapperTest
        extends AbstractMapperTest<ArtifactVote, ArtifactVoteMask, ArtifactVoteMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public ArtifactVote buildSample() {
        return ArtifactVoteSamples.build();
    }

}
