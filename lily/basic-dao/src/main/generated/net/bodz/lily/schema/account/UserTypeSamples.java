package net.bodz.lily.schema.account;

import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class UserTypeSamples
        extends TestSampleBuilder {

    @Override
    public UserType build()
            throws Exception {
        UserType a = new UserType();
        a.setName("Ft");
        a.setDummy(159598079);
        return a;
    }

    @Override
    public UserTypeSamples wireAny(IRandomPicker picker) {
        return this;
    }

    @Override
    public UserType buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
