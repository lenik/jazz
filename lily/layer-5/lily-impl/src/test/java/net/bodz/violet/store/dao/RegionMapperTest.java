package net.bodz.violet.store.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.dao.ArtifactCategoryMapper;
import net.bodz.violet.art.dao.ArtifactMapper;
import net.bodz.violet.store.Region;
import net.bodz.violet.store.RegionSamples;

public class RegionMapperTest
        extends AbstractTableTest<Region, RegionMask, RegionMapper> {

    @Override
    public Region buildSample()
            throws Exception {
        RegionSamples a = new RegionSamples();
        a.forCat = tables.pickAny(ArtifactCategoryMapper.class, "artcat");
        a.proto = tables.pickAny(RegionMapper.class, "region");
        a.tag = tables.pickAny(RegionTagMapper.class, "regiontag");
        a.category = tables.pickAny(RegionCategoryMapper.class, "regioncat");
        a.parent = tables.pickAny(RegionMapper.class, "region");
        a.level = tables.pickAny(RegionLevelMapper.class, "regionlevel");
        a.artifact = tables.pickAny(ArtifactMapper.class, "art");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.material = tables.pickAny(ArtifactMapper.class, "art");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.forArt = tables.pickAny(ArtifactMapper.class, "art");
        return a.build();
    }

}
