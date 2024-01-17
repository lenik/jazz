package net.bodz.lily.vapp.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.lily.vapp.VApp;
import net.bodz.lily.vapp.VAppSamples;

public class VAppManagerTest
        extends AbstractManagerTest<VApp, VAppMapper, VAppManager> {

    @Override
    public VApp buildSample()
            throws Exception {
        VAppSamples a = new VAppSamples();
        return a.buildWired(tables);
    }

}
