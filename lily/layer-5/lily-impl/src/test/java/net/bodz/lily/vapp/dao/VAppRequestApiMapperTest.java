package net.bodz.lily.vapp.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.lily.vapp.VAppRequestApi;
import net.bodz.lily.vapp.VAppRequestApiSamples;

public class VAppRequestApiMapperTest
        extends AbstractTableTest<VAppRequestApi, VAppRequestApiMapper> {

    @Override
    public VAppRequestApi buildSample()
            throws Exception {
        VAppRequestApiSamples a = new VAppRequestApiSamples();
        return a.buildWired(tables);
    }

}
