package net.bodz.violet.asset.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.contact.Person;
import net.bodz.lily.contact.impl.PersonMapper;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.art.impl.ArtifactMapper;
import net.bodz.violet.asset.Asset;
import net.bodz.violet.asset.AssetSamples;
import net.bodz.violet.store.Region;
import net.bodz.violet.store.impl.RegionMapper;

public class AssetMapperTest
        extends AbstractMapperTest<Asset, AssetMask, AssetMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public Asset buildSample() {
        Artifact artifact = tables.pickAny(ArtifactMapper.class, "art");
        Region region = tables.pickAny(RegionMapper.class, "region");
        Person person = tables.pickAny(PersonMapper.class, "person");
        return AssetSamples.build(artifact, region, person);
    }

}
