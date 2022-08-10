package net.bodz.violet.asset.impl;

import net.bodz.lily.contact.OrgUnit;
import net.bodz.lily.contact.impl.OrgUnitMapper;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.art.impl.ArtifactMapper;
import net.bodz.violet.asset.OrgUnitAsset;
import net.bodz.violet.asset.OrgUnitAssetSamples;
import net.bodz.violet.store.Region;
import net.bodz.violet.store.impl.RegionMapper;

public class OrgUnitAssetMapperTest
        extends AbstractMapperTest<OrgUnitAsset, AssetMask, OrgUnitAssetMapper> {

    @Override
    public OrgUnitAsset buildSample() {
        Artifact artifact = tables.pickAny(ArtifactMapper.class, "art");
        Region region = tables.pickAny(RegionMapper.class, "region");
        OrgUnit owner = tables.pickAny(OrgUnitMapper.class, "orgunit");
        return OrgUnitAssetSamples.build(artifact, region, owner);
    }

}
