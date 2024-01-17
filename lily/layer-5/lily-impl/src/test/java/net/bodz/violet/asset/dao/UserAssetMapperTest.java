package net.bodz.violet.asset.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.dao.ArtifactMapper;
import net.bodz.violet.asset.UserAsset;
import net.bodz.violet.asset.UserAssetSamples;
import net.bodz.violet.store.dao.RegionMapper;

public class UserAssetMapperTest
        extends AbstractTableTest<UserAsset, UserAssetCriteriaBuilder, UserAssetMapper> {

    @Override
    public UserAsset buildSample()
            throws Exception {
        UserAssetSamples a = new UserAssetSamples();
        a.region = tables.pickAny(RegionMapper.class, "region");
        a.owner = tables.pickAny(UserMapper.class, "user");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.artifact = tables.pickAny(ArtifactMapper.class, "art");
        return a.build();
    }

}
