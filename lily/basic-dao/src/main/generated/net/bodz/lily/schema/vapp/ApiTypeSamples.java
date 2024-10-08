package net.bodz.lily.schema.vapp;

import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class ApiTypeSamples
        extends TestSampleBuilder {

    @Override
    public ApiType build()
            throws Exception {
        ApiType a = new ApiType();
        a.setCode("Quyaf.");
        a.setUom("Gyavu. wnh afae zaikoov mii?");
        return a;
    }

    @Override
    public ApiTypeSamples wireAny(IRandomPicker picker) {
        return this;
    }

    @Override
    public ApiType buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
