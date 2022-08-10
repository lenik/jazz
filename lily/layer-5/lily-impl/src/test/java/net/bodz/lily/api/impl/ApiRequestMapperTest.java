package net.bodz.lily.api.impl;

import net.bodz.lily.api.Api;
import net.bodz.lily.api.ApiRequest;
import net.bodz.lily.api.ApiRequestSamples;
import net.bodz.lily.api.AppApply;
import net.bodz.lily.test.AbstractMapperTest;

public class ApiRequestMapperTest
        extends AbstractMapperTest<ApiRequest, ApiRequestMask, ApiRequestMapper> {

    @Override
    public ApiRequest buildSample() {
        AppApply apply = tables.pickAny(AppApplyMapper.class, "apply");
        Api api = tables.pickAny(ApiMapper.class, "api");
        return ApiRequestSamples.build(apply, api);
    }

}
