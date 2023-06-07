package net.bodz.violet.art.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.art.ArtifactSamples;

public class ArtifactMapperTest
        extends AbstractTableTest<Artifact, ArtifactMask, ArtifactMapper> {

    @Override
    public Artifact buildSample()
            throws Exception {
        ArtifactSamples a = new ArtifactSamples();
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.proto = tables.pickAny(ArtifactMapper.class, "art");
        a.phase = tables.pickAny(ArtifactPhaseMapper.class, "artphase");
        a.uom = tables.pickAny(UOMMapper.class, "uom");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.category = tables.pickAny(ArtifactCategoryMapper.class, "artcat");
        return a.build();
    }

}
