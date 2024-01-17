package net.bodz.lily.vapp.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.lily.vapp.VApi;
import net.bodz.lily.vapp.VApiSamples;

public class VApiManagerTest
        extends AbstractManagerTest<VApi, VApiMapper, VApiManager> {

    @Override
    public VApi buildSample()
            throws Exception {
        VApiSamples a = new VApiSamples();
        return a.buildWired(tables);
    }

}
