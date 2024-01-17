package net.bodz.lily.vapp.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.lily.vapp.VApi;
import net.bodz.lily.vapp.VApiSamples;

public class VApiMapperTest
        extends AbstractTableTest<VApi, VApiCriteriaBuilder, VApiMapper> {

    @Override
    public VApi buildSample()
            throws Exception {
        VApiSamples a = new VApiSamples();
        a.api = tables.pickAny(ApiTypeMapper.class, "apitype");
        a.app = tables.pickAny(VAppMapper.class, "vapp");
        return a.build();
    }

}
