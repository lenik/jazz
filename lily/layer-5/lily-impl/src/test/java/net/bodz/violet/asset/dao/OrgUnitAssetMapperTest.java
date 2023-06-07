package net.bodz.violet.asset.dao;

import net.bodz.lily.contact.dao.OrgUnitMapper;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.dao.ArtifactMapper;
import net.bodz.violet.asset.OrgUnitAsset;
import net.bodz.violet.asset.OrgUnitAssetSamples;
import net.bodz.violet.store.dao.RegionMapper;

public class OrgUnitAssetMapperTest
        extends AbstractTableTest<OrgUnitAsset, OrgUnitAssetMask, OrgUnitAssetMapper> {

    @Override
    public OrgUnitAsset buildSample()
            throws Exception {
        OrgUnitAssetSamples a = new OrgUnitAssetSamples();
        a.owner = tables.pickAny(OrgUnitMapper.class, "orgunit");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.region = tables.pickAny(RegionMapper.class, "region");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.artifact = tables.pickAny(ArtifactMapper.class, "art");
        return a.build();
    }

}
