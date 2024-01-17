package net.bodz.violet.art.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.ArtifactPhase;
import net.bodz.violet.art.ArtifactPhaseSamples;

public class ArtifactPhaseMapperTest
        extends AbstractTableTest<ArtifactPhase, ArtifactPhaseCriteriaBuilder, ArtifactPhaseMapper> {

    @Override
    public ArtifactPhase buildSample()
            throws Exception {
        ArtifactPhaseSamples a = new ArtifactPhaseSamples();
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        return a.build();
    }

}
