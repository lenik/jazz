package net.bodz.violet.art.dao;

import net.bodz.lily.schema.dao.FormDefMapper;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.ArtifactDoc;
import net.bodz.violet.art.ArtifactDocSamples;

public class ArtifactDocMapperTest
        extends AbstractTableTest<ArtifactDoc, ArtifactDocMask, ArtifactDocMapper> {

    @Override
    public ArtifactDoc buildSample()
            throws Exception {
        ArtifactDocSamples a = new ArtifactDocSamples();
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.artifact = tables.pickAny(ArtifactMapper.class, "art");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.op = tables.pickAny(UserMapper.class, "user");
        a.form = tables.pickAny(FormDefMapper.class, "_form");
        return a.build();
    }

}
