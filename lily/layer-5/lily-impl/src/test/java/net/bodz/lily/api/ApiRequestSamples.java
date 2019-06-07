package net.bodz.lily.api;

import net.bodz.lily.test.TestSamples;

public class ApiRequestSamples
        extends TestSamples {

    public static ApiRequest build(AppApply apply, Api api) {
        ApiRequest a = new ApiRequest();
        a.setApply(apply);
        a.setApi(api);
        return a;
    }

}
