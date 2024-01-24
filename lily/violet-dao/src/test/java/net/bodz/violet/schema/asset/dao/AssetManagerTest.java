package net.bodz.violet.schema.asset.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.asset.Asset;
import net.bodz.violet.schema.asset.AssetSamples;

public class AssetManagerTest
        extends AbstractManagerTest<Asset, AssetMapper, AssetManager> {

    @Override
    public Asset buildSample()
            throws Exception {
        AssetSamples a = new AssetSamples();
        return a.buildWired(tables);
    }

}
