package net.bodz.violet.art.dao;

import net.bodz.lily.inet.dao.ExternalSiteMapper;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.ArtifactBackref;
import net.bodz.violet.art.ArtifactBackrefSamples;

public class ArtifactBackrefMapperTest
        extends AbstractTableTest<ArtifactBackref, ArtifactBackrefMask, ArtifactBackrefMapper> {

    @Override
    public ArtifactBackref buildSample()
            throws Exception {
        ArtifactBackrefSamples a = new ArtifactBackrefSamples();
        a.site = tables.pickAny(ExternalSiteMapper.class, "extsite");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.artifact = tables.pickAny(ArtifactMapper.class, "art");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
