package net.bodz.lily.schema.vapp.dao;

import net.bodz.lily.schema.vapp.VAppRequestApi;
import net.bodz.lily.schema.vapp.VAppRequestApiSamples;
import net.bodz.lily.test.AbstractTableTest;

public class VAppRequestApiMapperTest
        extends AbstractTableTest<VAppRequestApi, VAppRequestApiMapper> {

    @Override
    public VAppRequestApi buildSample()
            throws Exception {
        VAppRequestApiSamples a = new VAppRequestApiSamples();
        return a.buildWired(tables);
    }

}
