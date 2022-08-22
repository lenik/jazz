package net.bodz.lily.api.impl;

import net.bodz.lily.api.Api;
import net.bodz.lily.api.ApiSamples;
import net.bodz.lily.test.AbstractTableTest;

public class ApiMapperTest
        extends AbstractTableTest<Api, ApiMask, ApiMapper> {

    @Override
    public Api buildSample() {
        return ApiSamples.build();
    }

}
