package net.bodz.violet.art.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.ArtifactParameter;
import net.bodz.violet.art.ArtifactParameterSamples;

public class ArtifactParameterMapperTest
        extends AbstractTableTest<ArtifactParameter, ArtifactParameterMask, ArtifactParameterMapper> {

    @Override
    public ArtifactParameter buildSample()
            throws Exception {
        ArtifactParameterSamples a = new ArtifactParameterSamples();
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
