package net.bodz.violet.store.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.art.impl.ArtifactMapper;
import net.bodz.violet.store.OffStoreItem;
import net.bodz.violet.store.OffStoreItemSamples;

public class OffStoreItemMapperTest
        extends AbstractMapperTest<OffStoreItem, OffStoreItemMask, OffStoreItemMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public OffStoreItem buildSample() {
        Artifact artifact = tables.pickAny(ArtifactMapper.class, "art");
        return OffStoreItemSamples.build(artifact);
    }

}
