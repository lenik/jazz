package net.bodz.lily.schema.vapp;

import net.bodz.lily.schema.vapp.dao.ApiTypeMapper;
import net.bodz.lily.schema.vapp.dao.VAppRequestMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class VAppRequestApiSamples
        extends TestSampleBuilder {

    public VAppRequest parent;
    public ApiType api;

    @Override
    public VAppRequestApi build()
            throws Exception {
        VAppRequestApi a = new VAppRequestApi();
        a.setParent(parent);
        a.setApi(api);
        a.setId(2583478279223025019L);
        return a;
    }

    @Override
    public VAppRequestApiSamples wireAny(IRandomPicker picker) {
        this.parent = picker.pickAny(VAppRequestMapper.class, "vappreq");
        this.api = picker.pickAny(ApiTypeMapper.class, "apitype");
        return this;
    }

    @Override
    public VAppRequestApi buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
