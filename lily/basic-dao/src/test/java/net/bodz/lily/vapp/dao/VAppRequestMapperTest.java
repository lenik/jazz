package net.bodz.lily.vapp.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.lily.vapp.VAppRequest;
import net.bodz.lily.vapp.VAppRequestSamples;

public class VAppRequestMapperTest
        extends AbstractTableTest<VAppRequest, VAppRequestMapper> {

    @Override
    public VAppRequest buildSample()
            throws Exception {
        VAppRequestSamples a = new VAppRequestSamples();
        return a.buildWired(tables);
    }

}
