package net.bodz.lily.schema.vapp.dao;

import net.bodz.lily.schema.vapp.VApiCredit;
import net.bodz.lily.schema.vapp.VApiCreditSamples;
import net.bodz.lily.test.AbstractTableTest;

public class VApiCreditMapperTest
        extends AbstractTableTest<VApiCredit, VApiCreditMapper> {

    @Override
    public VApiCredit buildSample()
            throws Exception {
        VApiCreditSamples a = new VApiCreditSamples();
        return a.buildWired(tables);
    }

}
