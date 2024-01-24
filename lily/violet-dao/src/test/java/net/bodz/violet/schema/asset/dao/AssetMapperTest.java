package net.bodz.violet.schema.asset.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.asset.Asset;
import net.bodz.violet.schema.asset.AssetSamples;

public class AssetMapperTest
        extends AbstractTableTest<Asset, AssetMapper> {

    @Override
    public Asset buildSample()
            throws Exception {
        AssetSamples a = new AssetSamples();
        return a.buildWired(tables);
    }

}
