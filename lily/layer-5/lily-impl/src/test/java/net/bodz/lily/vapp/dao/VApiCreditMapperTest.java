package net.bodz.lily.vapp.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.lily.vapp.VApiCredit;
import net.bodz.lily.vapp.VApiCreditSamples;

public class VApiCreditMapperTest
        extends AbstractTableTest<VApiCredit, VApiCreditMask, VApiCreditMapper> {

    @Override
    public VApiCredit buildSample()
            throws Exception {
        VApiCreditSamples a = new VApiCreditSamples();
        a.api = tables.pickAny(ApiTypeMapper.class, "apitype");
        a.app = tables.pickAny(VAppMapper.class, "vapp");
        return a.build();
    }

}
