package net.bodz.lily.vapp.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.lily.vapp.VApiCredit;
import net.bodz.lily.vapp.VApiCreditSamples;

public class VApiCreditManagerTest
        extends AbstractManagerTest<VApiCredit, VApiCreditMapper, VApiCreditManager> {

    @Override
    public VApiCredit buildSample()
            throws Exception {
        VApiCreditSamples a = new VApiCreditSamples();
        return a.buildWired(tables);
    }

}
