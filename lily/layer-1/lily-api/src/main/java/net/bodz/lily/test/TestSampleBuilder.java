package net.bodz.lily.test;

import java.util.Random;

import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType
public abstract class TestSampleBuilder {

    protected static final Random random = new Random();

    public abstract Object build()
            throws Exception;

}
