package net.bodz.lily.schema.vapp.dao;

import net.bodz.lily.schema.vapp.VApi;
import net.bodz.lily.schema.vapp.VApiSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class VApiManagerTest
        extends AbstractManagerTest<VApi, VApiMapper, VApiManager> {

    @Override
    public VApi buildSample()
            throws Exception {
        VApiSamples a = new VApiSamples();
        return a.buildWired(tables);
    }

}
