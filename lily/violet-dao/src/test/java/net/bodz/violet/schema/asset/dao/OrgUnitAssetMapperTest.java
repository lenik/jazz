package net.bodz.violet.schema.asset.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.asset.OrgUnitAsset;
import net.bodz.violet.schema.asset.OrgUnitAssetSamples;

public class OrgUnitAssetMapperTest
        extends AbstractTableTest<OrgUnitAsset, OrgUnitAssetMapper> {

    @Override
    public OrgUnitAsset buildSample()
            throws Exception {
        OrgUnitAssetSamples a = new OrgUnitAssetSamples();
        return a.buildWired(tables);
    }

}
