package net.bodz.violet.schema.fab.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.fab.FabProcessSerial;
import net.bodz.violet.schema.fab.FabProcessSerialSamples;

public class FabProcessSerialManagerTest
        extends AbstractManagerTest<FabProcessSerial, FabProcessSerialMapper, FabProcessSerialManager> {

    @Override
    public FabProcessSerial buildSample()
            throws Exception {
        FabProcessSerialSamples a = new FabProcessSerialSamples();
        return a.buildWired(tables);
    }

}
