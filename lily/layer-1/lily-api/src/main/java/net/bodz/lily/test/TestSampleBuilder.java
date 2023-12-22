package net.bodz.lily.test;

import java.util.Random;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.lily.util.IRandomPicker;

@IndexedType
public abstract class TestSampleBuilder {

    protected static final Random random = new Random();

    public abstract Object build()
            throws Exception;

    public TestSampleBuilder wireAny(IRandomPicker picker) {
        return this;
    }

    public Object buildWired(IRandomPicker picker)
            throws Exception {
        return wireAny(picker).build();
    }

}
