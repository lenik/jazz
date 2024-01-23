package net.bodz.violet.asset.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.asset.OrgUnitAsset;
import net.bodz.violet.asset.OrgUnitAssetSamples;

public class OrgUnitAssetMapperTest
        extends AbstractTableTest<OrgUnitAsset, OrgUnitAssetMapper> {

    @Override
    public OrgUnitAsset buildSample()
            throws Exception {
        OrgUnitAssetSamples a = new OrgUnitAssetSamples();
        return a.buildWired(tables);
    }

}
