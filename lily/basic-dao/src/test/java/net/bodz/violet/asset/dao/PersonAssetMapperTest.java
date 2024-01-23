package net.bodz.violet.asset.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.asset.PersonAsset;
import net.bodz.violet.asset.PersonAssetSamples;

public class PersonAssetMapperTest
        extends AbstractTableTest<PersonAsset, PersonAssetMapper> {

    @Override
    public PersonAsset buildSample()
            throws Exception {
        PersonAssetSamples a = new PersonAssetSamples();
        return a.buildWired(tables);
    }

}
