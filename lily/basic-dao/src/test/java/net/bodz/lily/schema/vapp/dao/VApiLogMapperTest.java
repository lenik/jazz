package net.bodz.lily.schema.vapp.dao;

import net.bodz.lily.schema.vapp.VApiLog;
import net.bodz.lily.schema.vapp.VApiLogSamples;
import net.bodz.lily.test.AbstractTableTest;

public class VApiLogMapperTest
        extends AbstractTableTest<VApiLog, VApiLogMapper> {

    @Override
    public VApiLog buildSample()
            throws Exception {
        VApiLogSamples a = new VApiLogSamples();
        return a.buildWired(tables);
    }

}
