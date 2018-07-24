package net.bodz.violet.asset.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.model.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.asset.Asset;
import net.bodz.violet.asset.AssetSamples;

public class AssetMapperTest
        extends AbstractMapperTest<Asset, AssetMask, AssetMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public Asset buildSample() {
        return AssetSamples.build();
    }

}
