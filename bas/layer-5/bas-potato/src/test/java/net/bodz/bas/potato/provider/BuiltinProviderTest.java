package net.bodz.bas.potato.provider;

import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.potato.spi.bean.BeanPotatoProvider;
import net.bodz.bas.potato.spi.reflect.ReflectPotatoProvider;
import net.bodz.bas.trait.Traits;
import net.bodz.bas.trait.spi.ITraitsProvider;

import org.junit.Assert;
import org.junit.Test;

public class BuiltinProviderTest
        extends Assert {

    static Set<Class<?>> traitsProviderTypes;
    static {
        traitsProviderTypes = new HashSet<Class<?>>();
        for (ITraitsProvider provider : Traits.getTraitsProviders())
            traitsProviderTypes.add(provider.getClass());
    }

    @Test
    public void testBeanPotatoProviderLoaded() {
        assertTrue(traitsProviderTypes.contains(BeanPotatoProvider.class));
    }

    @Test
    public void testReflectPotatoProviderLoaded() {
        assertTrue(traitsProviderTypes.contains(ReflectPotatoProvider.class));
    }

}
