package net.bodz.bas.c.type;

import org.junit.Assert;
import org.junit.Test;

public class CachedInstantiatorTest
        extends Assert {

    CachedInstantiator cache = CachedInstantiator.getInstance();

    public static class Box {
        public int size;
    }

    @Test
    public void testRepeat() {
        Box box = cache.instantiate(Box.class);
        Box box2 = cache.instantiate(Box.class);
        assert box == box2;
    }

}
