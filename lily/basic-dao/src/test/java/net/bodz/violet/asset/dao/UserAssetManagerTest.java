package net.bodz.violet.asset.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.asset.UserAsset;
import net.bodz.violet.asset.UserAssetSamples;

public class UserAssetManagerTest
        extends AbstractManagerTest<UserAsset, UserAssetMapper, UserAssetManager> {

    @Override
    public UserAsset buildSample()
            throws Exception {
        UserAssetSamples a = new UserAssetSamples();
        return a.buildWired(tables);
    }

}
