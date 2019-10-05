package net.bodz.bas.t.predef;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.t.variant.conv.IVarConverter;

public class PredefVarConvertersTest
        extends Assert {

    @Test
    public void testExists() {
        PredefVarConverters provider = new PredefVarConverters();
        Collection<IVarConverter<?>> converters = provider.getConverters();
        assertFalse(converters.isEmpty());
    }

}
