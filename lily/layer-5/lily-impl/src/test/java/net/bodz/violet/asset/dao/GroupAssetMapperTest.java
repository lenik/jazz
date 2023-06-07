package net.bodz.violet.asset.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.dao.ArtifactMapper;
import net.bodz.violet.asset.GroupAsset;
import net.bodz.violet.asset.GroupAssetSamples;
import net.bodz.violet.store.dao.RegionMapper;

public class GroupAssetMapperTest
        extends AbstractTableTest<GroupAsset, GroupAssetMask, GroupAssetMapper> {

    @Override
    public GroupAsset buildSample()
            throws Exception {
        GroupAssetSamples a = new GroupAssetSamples();
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.owner = tables.pickAny(GroupMapper.class, "group");
        a.artifact = tables.pickAny(ArtifactMapper.class, "art");
        a.region = tables.pickAny(RegionMapper.class, "region");
        return a.build();
    }

}
