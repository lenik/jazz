package net.bodz.lily.security;

import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class UserOtherIdTypeSamples
        extends TestSampleBuilder {

    @Override
    public UserOtherIdType build()
            throws Exception {
        UserOtherIdType a = new UserOtherIdType();
        a.setId(2123102264);
        a.setDummy(1942475375);
        return a;
    }

    @Override
    public UserOtherIdTypeSamples wireAny(IRandomPicker picker) {
        return this;
    }

    @Override
    public UserOtherIdType buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
