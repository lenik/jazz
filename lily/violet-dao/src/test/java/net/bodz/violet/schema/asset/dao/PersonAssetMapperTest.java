package net.bodz.violet.schema.asset.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.asset.PersonAsset;
import net.bodz.violet.schema.asset.PersonAssetSamples;

public class PersonAssetMapperTest
        extends AbstractTableTest<PersonAsset, PersonAssetMapper> {

    @Override
    public PersonAsset buildSample()
            throws Exception {
        PersonAssetSamples a = new PersonAssetSamples();
        return a.buildWired(tables);
    }

}
