package net.bodz.lily.schema.vapp.dao;

import net.bodz.lily.schema.vapp.VApiCredit;
import net.bodz.lily.schema.vapp.VApiCreditSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class VApiCreditManagerTest
        extends AbstractManagerTest<VApiCredit, VApiCreditMapper, VApiCreditManager> {

    @Override
    public VApiCredit buildSample()
            throws Exception {
        VApiCreditSamples a = new VApiCreditSamples();
        return a.buildWired(tables);
    }

}
