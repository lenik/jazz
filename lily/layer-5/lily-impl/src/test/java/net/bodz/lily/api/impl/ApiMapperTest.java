package net.bodz.lily.api.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.api.Api;
import net.bodz.lily.api.ApiSamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;

public class ApiMapperTest
        extends AbstractMapperTest<Api, ApiMask, ApiMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public Api buildSample() {
        return ApiSamples.build();
    }

}
