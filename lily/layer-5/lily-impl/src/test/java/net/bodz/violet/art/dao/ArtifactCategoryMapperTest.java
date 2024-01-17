package net.bodz.violet.art.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.ArtifactCategory;
import net.bodz.violet.art.ArtifactCategorySamples;

public class ArtifactCategoryMapperTest
        extends AbstractTableTest<ArtifactCategory, ArtifactCategoryCriteriaBuilder, ArtifactCategoryMapper> {

    @Override
    public ArtifactCategory buildSample()
            throws Exception {
        ArtifactCategorySamples a = new ArtifactCategorySamples();
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.parent = tables.pickAny(ArtifactCategoryMapper.class, "artcat");
        return a.build();
    }

}
