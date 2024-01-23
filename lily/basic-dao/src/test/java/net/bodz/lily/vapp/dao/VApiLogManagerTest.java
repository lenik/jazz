package net.bodz.lily.vapp.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.lily.vapp.VApiLog;
import net.bodz.lily.vapp.VApiLogSamples;

public class VApiLogManagerTest
        extends AbstractManagerTest<VApiLog, VApiLogMapper, VApiLogManager> {

    @Override
    public VApiLog buildSample()
            throws Exception {
        VApiLogSamples a = new VApiLogSamples();
        return a.buildWired(tables);
    }

}
