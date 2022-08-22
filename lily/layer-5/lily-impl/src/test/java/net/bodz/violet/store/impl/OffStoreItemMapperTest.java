package net.bodz.violet.store.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.art.impl.ArtifactMapper;
import net.bodz.violet.store.OffStoreItem;
import net.bodz.violet.store.OffStoreItemSamples;

public class OffStoreItemMapperTest
        extends AbstractTableTest<OffStoreItem, OffStoreItemMask, OffStoreItemMapper> {

    @Override
    public OffStoreItem buildSample() {
        Artifact artifact = tables.pickAny(ArtifactMapper.class, "art");
        return OffStoreItemSamples.build(artifact);
    }

}
