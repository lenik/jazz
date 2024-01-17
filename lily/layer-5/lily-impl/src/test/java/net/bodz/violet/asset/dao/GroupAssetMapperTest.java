package net.bodz.violet.asset.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.asset.GroupAsset;
import net.bodz.violet.asset.GroupAssetSamples;

public class GroupAssetMapperTest
        extends AbstractTableTest<GroupAsset, GroupAssetMapper> {

    @Override
    public GroupAsset buildSample()
            throws Exception {
        GroupAssetSamples a = new GroupAssetSamples();
        return a.buildWired(tables);
    }

}
