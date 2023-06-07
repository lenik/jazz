package net.bodz.violet.art.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.ArtifactTag;
import net.bodz.violet.art.ArtifactTagSamples;

public class ArtifactTagMapperTest
        extends AbstractTableTest<ArtifactTag, ArtifactTagMask, ArtifactTagMapper> {

    @Override
    public ArtifactTag buildSample()
            throws Exception {
        ArtifactTagSamples a = new ArtifactTagSamples();
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.parent = tables.pickAny(ArtifactTagMapper.class, "arttag");
        return a.build();
    }

}
