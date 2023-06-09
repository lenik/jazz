package net.bodz.lily.vapp;

import net.bodz.lily.test.TestSampleBuilder;

public class VAppRequestApiSamples
        extends TestSampleBuilder {

    public VAppRequest parent;
    public ApiType api;

    public VAppRequestApi build()
            throws Exception {
        VAppRequestApi a = new VAppRequestApi();
        a.setParent(parent);
        a.setApi(api);
        a.setId(2583478279223025019L);
        return a;
    }

}
