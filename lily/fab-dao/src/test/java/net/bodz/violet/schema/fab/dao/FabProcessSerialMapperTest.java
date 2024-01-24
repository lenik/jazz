package net.bodz.violet.schema.fab.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.fab.FabProcessSerial;
import net.bodz.violet.schema.fab.FabProcessSerialSamples;

public class FabProcessSerialMapperTest
        extends AbstractTableTest<FabProcessSerial, FabProcessSerialMapper> {

    @Override
    public FabProcessSerial buildSample()
            throws Exception {
        FabProcessSerialSamples a = new FabProcessSerialSamples();
        return a.buildWired(tables);
    }

}
