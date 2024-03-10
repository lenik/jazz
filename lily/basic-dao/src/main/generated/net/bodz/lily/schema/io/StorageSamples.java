package net.bodz.lily.schema.io;

import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class StorageSamples
        extends TestSampleBuilder {

    @Override
    public Storage build()
            throws Exception {
        Storage a = new Storage();
        a.setName("Hew ae");
        return a;
    }

    @Override
    public StorageSamples wireAny(IRandomPicker picker) {
        return this;
    }

    @Override
    public Storage buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
