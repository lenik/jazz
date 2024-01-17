package net.bodz.violet.asset.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.asset.GroupAsset;
import net.bodz.violet.asset.GroupAssetSamples;

public class GroupAssetManagerTest
        extends AbstractManagerTest<GroupAsset, GroupAssetMapper, GroupAssetManager> {

    @Override
    public GroupAsset buildSample()
            throws Exception {
        GroupAssetSamples a = new GroupAssetSamples();
        return a.buildWired(tables);
    }

}
