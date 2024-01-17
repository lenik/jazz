package net.bodz.lily.vapp.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.lily.vapp.ApiType;
import net.bodz.lily.vapp.ApiTypeSamples;

public class ApiTypeManagerTest
        extends AbstractManagerTest<ApiType, ApiTypeMapper, ApiTypeManager> {

    @Override
    public ApiType buildSample()
            throws Exception {
        ApiTypeSamples a = new ApiTypeSamples();
        return a.buildWired(tables);
    }

}
