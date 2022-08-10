package net.bodz.lily.api.impl;

import net.bodz.lily.api.Api;
import net.bodz.lily.api.ApiGranted;
import net.bodz.lily.api.ApiGrantedSamples;
import net.bodz.lily.api.App;
import net.bodz.lily.test.AbstractMapperTest;

public class ApiGrantedMapperTest
        extends AbstractMapperTest<ApiGranted, ApiGrantedMask, ApiGrantedMapper> {

    @Override
    public ApiGranted buildSample() {
        App app = tables.pickAny(AppMapper.class, "app");
        Api api = tables.pickAny(ApiMapper.class, "api");
        return ApiGrantedSamples.build(app, api);
    }

}
