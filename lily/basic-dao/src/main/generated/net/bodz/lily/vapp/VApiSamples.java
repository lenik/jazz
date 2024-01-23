package net.bodz.lily.vapp;

import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.lily.vapp.dao.ApiTypeMapper;
import net.bodz.lily.vapp.dao.VAppMapper;

public class VApiSamples
        extends TestSampleBuilder {

    public ApiType api;
    public VApp app;

    @Override
    public VApi build()
            throws Exception {
        VApi a = new VApi();
        a.setApi(api);
        a.setApp(app);
        a.setId(5965593489077467774L);
        a.setCallback("Agegi! uquo oil ahbt ir z f niruq");
        return a;
    }

    @Override
    public VApiSamples wireAny(IRandomPicker picker) {
        this.api = picker.pickAny(ApiTypeMapper.class, "apitype");
        this.app = picker.pickAny(VAppMapper.class, "vapp");
        return this;
    }

    @Override
    public VApi buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
