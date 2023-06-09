package net.bodz.lily.vapp;

import net.bodz.lily.test.TestSampleBuilder;

public class VApiSamples
        extends TestSampleBuilder {

    public ApiType api;
    public VApp app;

    public VApi build()
            throws Exception {
        VApi a = new VApi();
        a.setApi(api);
        a.setApp(app);
        a.setId(5965593489077467774L);
        a.setCallback("Agegi! uquo oil ahbt ir z f niruq");
        return a;
    }

}
