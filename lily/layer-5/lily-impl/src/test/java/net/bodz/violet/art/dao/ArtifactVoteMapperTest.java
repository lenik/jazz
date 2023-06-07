package net.bodz.violet.art.dao;

import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.ArtifactVote;
import net.bodz.violet.art.ArtifactVoteSamples;

public class ArtifactVoteMapperTest
        extends AbstractTableTest<ArtifactVote, ArtifactVoteMask, ArtifactVoteMapper> {

    @Override
    public ArtifactVote buildSample()
            throws Exception {
        ArtifactVoteSamples a = new ArtifactVoteSamples();
        a.user = tables.pickAny(UserMapper.class, "user");
        a.parent = tables.pickAny(ArtifactMapper.class, "art");
        return a.build();
    }

}
