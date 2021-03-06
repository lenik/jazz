package net.bodz.violet.asset.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.contact.Person;
import net.bodz.lily.contact.impl.PersonMapper;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.art.impl.ArtifactMapper;
import net.bodz.violet.asset.PersonAsset;
import net.bodz.violet.asset.PersonAssetSamples;
import net.bodz.violet.store.Region;
import net.bodz.violet.store.impl.RegionMapper;

public class PersonAssetMapperTest
        extends AbstractMapperTest<PersonAsset, AssetMask, PersonAssetMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public PersonAsset buildSample() {
        Artifact artifact = tables.pickAny(ArtifactMapper.class, "art");
        Region region = tables.pickAny(RegionMapper.class, "region");
        Person owner = tables.pickAny(PersonMapper.class, "person");
        return PersonAssetSamples.build(artifact, region, owner);
    }

}
