package net.bodz.lily.api.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.api.Api;
import net.bodz.lily.api.ApiSamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;

public class ApiMapperTest
        extends AbstractMapperTest<Api, ApiMask, ApiMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public Api buildSample() {
        return ApiSamples.build();
    }

}
