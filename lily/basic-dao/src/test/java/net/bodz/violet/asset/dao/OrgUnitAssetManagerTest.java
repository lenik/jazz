package net.bodz.violet.asset.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.asset.OrgUnitAsset;
import net.bodz.violet.asset.OrgUnitAssetSamples;

public class OrgUnitAssetManagerTest
        extends AbstractManagerTest<OrgUnitAsset, OrgUnitAssetMapper, OrgUnitAssetManager> {

    @Override
    public OrgUnitAsset buildSample()
            throws Exception {
        OrgUnitAssetSamples a = new OrgUnitAssetSamples();
        return a.buildWired(tables);
    }

}
