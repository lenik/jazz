package net.bodz.lily.vapp.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.lily.vapp.VApiLog;
import net.bodz.lily.vapp.VApiLogSamples;

public class VApiLogMapperTest
        extends AbstractTableTest<VApiLog, VApiLogMapper> {

    @Override
    public VApiLog buildSample()
            throws Exception {
        VApiLogSamples a = new VApiLogSamples();
        return a.buildWired(tables);
    }

}
