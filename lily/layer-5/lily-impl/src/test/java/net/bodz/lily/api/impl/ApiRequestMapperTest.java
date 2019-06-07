package net.bodz.lily.api.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.api.Api;
import net.bodz.lily.api.ApiRequest;
import net.bodz.lily.api.ApiRequestSamples;
import net.bodz.lily.api.AppApply;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;

public class ApiRequestMapperTest
        extends AbstractMapperTest<ApiRequest, ApiRequestMask, ApiRequestMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ApiRequest buildSample() {
        AppApply apply = tables.pickAny(AppApplyMapper.class, "apply");
        Api api = tables.pickAny(ApiMapper.class, "api");
        return ApiRequestSamples.build(apply, api);
    }

}
