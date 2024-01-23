package net.bodz.lily.vapp.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.lily.vapp.VApi;
import net.bodz.lily.vapp.VApiSamples;

public class VApiMapperTest
        extends AbstractTableTest<VApi, VApiMapper> {

    @Override
    public VApi buildSample()
            throws Exception {
        VApiSamples a = new VApiSamples();
        return a.buildWired(tables);
    }

}
