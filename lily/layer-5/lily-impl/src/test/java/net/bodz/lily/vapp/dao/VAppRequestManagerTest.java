package net.bodz.lily.vapp.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.lily.vapp.VAppRequest;
import net.bodz.lily.vapp.VAppRequestSamples;

public class VAppRequestManagerTest
        extends AbstractManagerTest<VAppRequest, VAppRequestMapper, VAppRequestManager> {

    @Override
    public VAppRequest buildSample()
            throws Exception {
        VAppRequestSamples a = new VAppRequestSamples();
        return a.buildWired(tables);
    }

}
