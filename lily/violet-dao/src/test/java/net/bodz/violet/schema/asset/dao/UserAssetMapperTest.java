package net.bodz.violet.schema.asset.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.asset.UserAsset;
import net.bodz.violet.schema.asset.UserAssetSamples;

public class UserAssetMapperTest
        extends AbstractTableTest<UserAsset, UserAssetMapper> {

    @Override
    public UserAsset buildSample()
            throws Exception {
        UserAssetSamples a = new UserAssetSamples();
        return a.buildWired(tables);
    }

}
