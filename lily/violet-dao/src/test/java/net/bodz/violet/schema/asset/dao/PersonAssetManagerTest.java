package net.bodz.violet.schema.asset.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.asset.PersonAsset;
import net.bodz.violet.schema.asset.PersonAssetSamples;

public class PersonAssetManagerTest
        extends AbstractManagerTest<PersonAsset, PersonAssetMapper, PersonAssetManager> {

    @Override
    public PersonAsset buildSample()
            throws Exception {
        PersonAssetSamples a = new PersonAssetSamples();
        return a.buildWired(tables);
    }

}
