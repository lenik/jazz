package net.bodz.violet.asset.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.asset.Asset;
import net.bodz.violet.asset.AssetSamples;

public class AssetManagerTest
        extends AbstractManagerTest<Asset, AssetMapper, AssetManager> {

    @Override
    public Asset buildSample()
            throws Exception {
        AssetSamples a = new AssetSamples();
        return a.buildWired(tables);
    }

}
