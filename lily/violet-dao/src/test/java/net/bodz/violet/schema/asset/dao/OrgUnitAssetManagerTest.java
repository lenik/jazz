package net.bodz.violet.schema.asset.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.asset.OrgUnitAsset;
import net.bodz.violet.schema.asset.OrgUnitAssetSamples;

public class OrgUnitAssetManagerTest
        extends AbstractManagerTest<OrgUnitAsset, OrgUnitAssetMapper, OrgUnitAssetManager> {

    @Override
    public OrgUnitAsset buildSample()
            throws Exception {
        OrgUnitAssetSamples a = new OrgUnitAssetSamples();
        return a.buildWired(tables);
    }

}
