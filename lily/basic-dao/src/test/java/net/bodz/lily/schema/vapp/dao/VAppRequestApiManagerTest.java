package net.bodz.lily.schema.vapp.dao;

import net.bodz.lily.schema.vapp.VAppRequestApi;
import net.bodz.lily.schema.vapp.VAppRequestApiSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class VAppRequestApiManagerTest
        extends AbstractManagerTest<VAppRequestApi, VAppRequestApiMapper, VAppRequestApiManager> {

    @Override
    public VAppRequestApi buildSample()
            throws Exception {
        VAppRequestApiSamples a = new VAppRequestApiSamples();
        return a.buildWired(tables);
    }

}
