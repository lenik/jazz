package net.bodz.violet.schema.asset.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.asset.OrgAsset;
import net.bodz.violet.schema.asset.OrgAssetSamples;

public class OrgAssetMapperTest
        extends AbstractTableTest<OrgAsset, OrgAssetMapper> {

    @Override
    public OrgAsset buildSample()
            throws Exception {
        OrgAssetSamples a = new OrgAssetSamples();
        return a.buildWired(tables);
    }

}
