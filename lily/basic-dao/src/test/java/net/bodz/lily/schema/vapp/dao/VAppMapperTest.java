package net.bodz.lily.schema.vapp.dao;

import net.bodz.lily.schema.vapp.VApp;
import net.bodz.lily.schema.vapp.VAppSamples;
import net.bodz.lily.test.AbstractTableTest;

public class VAppMapperTest
        extends AbstractTableTest<VApp, VAppMapper> {

    @Override
    public VApp buildSample()
            throws Exception {
        VAppSamples a = new VAppSamples();
        return a.buildWired(tables);
    }

}
