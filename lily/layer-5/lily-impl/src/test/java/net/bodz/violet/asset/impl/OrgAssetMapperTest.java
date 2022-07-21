package net.bodz.violet.asset.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.contact.Organization;
import net.bodz.lily.contact.impl.OrganizationMapper;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.art.impl.ArtifactMapper;
import net.bodz.violet.asset.OrgAsset;
import net.bodz.violet.asset.OrgAssetSamples;
import net.bodz.violet.store.Region;
import net.bodz.violet.store.impl.RegionMapper;

public class OrgAssetMapperTest
        extends AbstractMapperTest<OrgAsset, AssetMask, OrgAssetMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public OrgAsset buildSample() {
        Artifact artifact = tables.pickAny(ArtifactMapper.class, "art");
        Region region = tables.pickAny(RegionMapper.class, "region");
        Organization owner = tables.pickAny(OrganizationMapper.class, "org");
        return OrgAssetSamples.build(artifact, region, owner);
    }

}
