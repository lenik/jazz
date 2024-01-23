package net.bodz.lily.vapp.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.lily.vapp.VAppRequestApi;
import net.bodz.lily.vapp.VAppRequestApiSamples;

public class VAppRequestApiManagerTest
        extends AbstractManagerTest<VAppRequestApi, VAppRequestApiMapper, VAppRequestApiManager> {

    @Override
    public VAppRequestApi buildSample()
            throws Exception {
        VAppRequestApiSamples a = new VAppRequestApiSamples();
        return a.buildWired(tables);
    }

}
