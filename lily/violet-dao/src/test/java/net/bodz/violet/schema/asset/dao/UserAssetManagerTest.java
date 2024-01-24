package net.bodz.violet.schema.asset.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.asset.UserAsset;
import net.bodz.violet.schema.asset.UserAssetSamples;

public class UserAssetManagerTest
        extends AbstractManagerTest<UserAsset, UserAssetMapper, UserAssetManager> {

    @Override
    public UserAsset buildSample()
            throws Exception {
        UserAssetSamples a = new UserAssetSamples();
        return a.buildWired(tables);
    }

}
