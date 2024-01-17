package net.bodz.violet.asset.dao;

import net.bodz.lily.contact.dao.OrgUnitMapper;
import net.bodz.lily.contact.dao.OrganizationMapper;
import net.bodz.lily.contact.dao.PersonMapper;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.dao.ArtifactMapper;
import net.bodz.violet.asset.Asset;
import net.bodz.violet.asset.AssetSamples;
import net.bodz.violet.store.dao.RegionMapper;

public class AssetMapperTest
        extends AbstractTableTest<Asset, AssetCriteriaBuilder, AssetMapper> {

    @Override
    public Asset buildSample()
            throws Exception {
        AssetSamples a = new AssetSamples();
        a.person = tables.pickAny(PersonMapper.class, "person");
        a.group = tables.pickAny(GroupMapper.class, "group");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.org = tables.pickAny(OrganizationMapper.class, "org");
        a.orgUnit = tables.pickAny(OrgUnitMapper.class, "orgunit");
        a.artifact = tables.pickAny(ArtifactMapper.class, "art");
        a.region = tables.pickAny(RegionMapper.class, "region");
        a.user = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
