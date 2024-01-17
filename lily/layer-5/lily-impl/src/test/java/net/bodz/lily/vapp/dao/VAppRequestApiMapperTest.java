package net.bodz.lily.vapp.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.lily.vapp.VAppRequestApi;
import net.bodz.lily.vapp.VAppRequestApiSamples;

public class VAppRequestApiMapperTest
        extends AbstractTableTest<VAppRequestApi, VAppRequestApiCriteriaBuilder, VAppRequestApiMapper> {

    @Override
    public VAppRequestApi buildSample()
            throws Exception {
        VAppRequestApiSamples a = new VAppRequestApiSamples();
        a.parent = tables.pickAny(VAppRequestMapper.class, "vappreq");
        a.api = tables.pickAny(ApiTypeMapper.class, "apitype");
        return a.build();
    }

}
