package net.bodz.lily.vapp;

import java.math.BigDecimal;

import net.bodz.lily.test.TestSampleBuilder;

public class VApiCreditSamples
        extends TestSampleBuilder {

    public ApiType api;
    public VApp app;

    public VApiCredit build()
            throws Exception {
        VApiCredit a = new VApiCredit();
        a.setApi(api);
        a.setApp(app);
        a.setId(179039370);
        a.setCredit(new BigDecimal("819072565616832"));
        return a;
    }

}
