package net.bodz.lily.vapp;

import java.math.BigDecimal;

import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.lily.vapp.dao.ApiTypeMapper;
import net.bodz.lily.vapp.dao.VAppMapper;

public class VApiCreditSamples
        extends TestSampleBuilder {

    public ApiType api;
    public VApp app;

    @Override
    public VApiCredit build()
            throws Exception {
        VApiCredit a = new VApiCredit();
        a.setApi(api);
        a.setApp(app);
        a.setId(179039370);
        a.setCredit(new BigDecimal("819072565616832"));
        return a;
    }

    @Override
    public VApiCreditSamples wireAny(IRandomPicker picker) {
        this.api = picker.pickAny(ApiTypeMapper.class, "apitype");
        this.app = picker.pickAny(VAppMapper.class, "vapp");
        return this;
    }

    @Override
    public VApiCredit buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
