package net.bodz.lily.schema.vapp.dao;

import net.bodz.lily.schema.vapp.VApi;
import net.bodz.lily.schema.vapp.VApiSamples;
import net.bodz.lily.test.AbstractTableTest;

public class VApiMapperTest
        extends AbstractTableTest<VApi, VApiMapper> {

    @Override
    public VApi buildSample()
            throws Exception {
        VApiSamples a = new VApiSamples();
        return a.buildWired(tables);
    }

}
