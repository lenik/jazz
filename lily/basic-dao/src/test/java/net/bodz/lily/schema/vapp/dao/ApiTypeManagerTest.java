package net.bodz.lily.schema.vapp.dao;

import net.bodz.lily.schema.vapp.ApiType;
import net.bodz.lily.schema.vapp.ApiTypeSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class ApiTypeManagerTest
        extends AbstractManagerTest<ApiType, ApiTypeMapper, ApiTypeManager> {

    @Override
    public ApiType buildSample()
            throws Exception {
        ApiTypeSamples a = new ApiTypeSamples();
        return a.buildWired(tables);
    }

}
