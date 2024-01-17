package net.bodz.lily.vapp.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.lily.vapp.VApp;
import net.bodz.lily.vapp.VAppSamples;

public class VAppMapperTest
        extends AbstractTableTest<VApp, VAppMapper> {

    @Override
    public VApp buildSample()
            throws Exception {
        VAppSamples a = new VAppSamples();
        return a.buildWired(tables);
    }

}
