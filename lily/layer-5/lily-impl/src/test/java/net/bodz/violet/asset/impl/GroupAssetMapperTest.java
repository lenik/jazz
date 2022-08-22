package net.bodz.violet.asset.impl;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.impl.GroupMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.art.impl.ArtifactMapper;
import net.bodz.violet.asset.GroupAsset;
import net.bodz.violet.asset.GroupAssetSamples;
import net.bodz.violet.store.Region;
import net.bodz.violet.store.impl.RegionMapper;

public class GroupAssetMapperTest
        extends AbstractTableTest<GroupAsset, AssetMask, GroupAssetMapper> {

    @Override
    public GroupAsset buildSample() {
        Artifact artifact = tables.pickAny(ArtifactMapper.class, "art");
        Region region = tables.pickAny(RegionMapper.class, "region");
        Group group = tables.pickAny(GroupMapper.class, "group");
        return GroupAssetSamples.build(artifact, region, group);
    }

}
