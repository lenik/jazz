package net.bodz.lily.schema.io.dao;

import net.bodz.lily.schema.io.Storage;
import net.bodz.lily.schema.io.StorageSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class StorageManagerTest
        extends AbstractManagerTest<Storage, StorageMapper, StorageManager> {

    @Override
    public Storage buildSample()
            throws Exception {
        StorageSamples a = new StorageSamples();
        return a.buildWired(tables);
    }

}
