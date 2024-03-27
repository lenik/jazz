package net.bodz.lily.schema.account;

import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class UserOtherIdTypeSamples
        extends TestSampleBuilder {

    @Override
    public UserOtherIdType build()
            throws Exception {
        UserOtherIdType a = new UserOtherIdType();
        a.setDummy(2123102264);
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
