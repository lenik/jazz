package net.bodz.violet.asset.dao;

import net.bodz.lily.contact.dao.OrganizationMapper;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.dao.ArtifactMapper;
import net.bodz.violet.asset.OrgAsset;
import net.bodz.violet.asset.OrgAssetSamples;
import net.bodz.violet.store.dao.RegionMapper;

public class OrgAssetMapperTest
        extends AbstractTableTest<OrgAsset, OrgAssetMask, OrgAssetMapper> {

    @Override
    public OrgAsset buildSample()
            throws Exception {
        OrgAssetSamples a = new OrgAssetSamples();
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.owner = tables.pickAny(OrganizationMapper.class, "org");
        a.region = tables.pickAny(RegionMapper.class, "region");
        a.artifact = tables.pickAny(ArtifactMapper.class, "art");
        return a.build();
    }

}
