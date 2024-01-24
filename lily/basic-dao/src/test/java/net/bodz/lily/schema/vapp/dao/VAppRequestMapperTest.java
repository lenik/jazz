package net.bodz.lily.schema.vapp.dao;

import net.bodz.lily.schema.vapp.VAppRequest;
import net.bodz.lily.schema.vapp.VAppRequestSamples;
import net.bodz.lily.test.AbstractTableTest;

public class VAppRequestMapperTest
        extends AbstractTableTest<VAppRequest, VAppRequestMapper> {

    @Override
    public VAppRequest buildSample()
            throws Exception {
        VAppRequestSamples a = new VAppRequestSamples();
        return a.buildWired(tables);
    }

}
