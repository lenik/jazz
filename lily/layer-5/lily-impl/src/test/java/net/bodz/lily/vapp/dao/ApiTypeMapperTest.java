package net.bodz.lily.vapp.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.lily.vapp.ApiType;
import net.bodz.lily.vapp.ApiTypeSamples;

public class ApiTypeMapperTest
        extends AbstractTableTest<ApiType, ApiTypeCriteriaBuilder, ApiTypeMapper> {

    @Override
    public ApiType buildSample()
            throws Exception {
        ApiTypeSamples a = new ApiTypeSamples();
        return a.build();
    }

}
