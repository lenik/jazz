package net.bodz.lily.schema.account;

import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class GroupTypeSamples
        extends TestSampleBuilder {

    @Override
    public GroupType build()
            throws Exception {
        GroupType a = new GroupType();
        a.setName("Onmc. kagt e aptfb.");
        a.setDummy(1588076486);
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
