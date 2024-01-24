package net.bodz.violet.schema.asset.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.asset.OrgAsset;
import net.bodz.violet.schema.asset.OrgAssetSamples;

public class OrgAssetManagerTest
        extends AbstractManagerTest<OrgAsset, OrgAssetMapper, OrgAssetManager> {

    @Override
    public OrgAsset buildSample()
            throws Exception {
        OrgAssetSamples a = new OrgAssetSamples();
        return a.buildWired(tables);
    }

}
