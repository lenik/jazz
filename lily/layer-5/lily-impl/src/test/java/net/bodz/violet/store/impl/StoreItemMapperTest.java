package net.bodz.violet.store.impl;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.art.impl.ArtifactMapper;
import net.bodz.violet.store.Region;
import net.bodz.violet.store.StoreItem;
import net.bodz.violet.store.StoreItemSamples;

public class StoreItemMapperTest
        extends AbstractMapperTest<StoreItem, StoreItemMask, StoreItemMapper> {

    @Override
    public StoreItem buildSample() {
        Artifact artifact = tables.pickAny(ArtifactMapper.class, "art");
        Region region = tables.pickAny(RegionMapper.class, "region");
        return StoreItemSamples.build(artifact, region);
    }

}
