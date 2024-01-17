package net.bodz.lily.security;

import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class GroupTypeSamples
        extends TestSampleBuilder {

    @Override
    public GroupType build()
            throws Exception {
        GroupType a = new GroupType();
        a.setId(1588076486);
        a.setDummy(1800975705);
        return a;
    }

    @Override
    public GroupTypeSamples wireAny(IRandomPicker picker) {
        return this;
    }

    @Override
    public GroupType buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
