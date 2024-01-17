package net.bodz.violet.asset.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.asset.Asset;
import net.bodz.violet.asset.AssetSamples;

public class AssetMapperTest
        extends AbstractTableTest<Asset, AssetMapper> {

    @Override
    public Asset buildSample()
            throws Exception {
        AssetSamples a = new AssetSamples();
        return a.buildWired(tables);
    }

}
