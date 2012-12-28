package net.bodz.bas.potato.provider;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.potato.provider.bean.BeanPotatoElementProvider;
import net.bodz.bas.potato.provider.reflect.ReflectPotatoElementProvider;
import net.bodz.bas.trait.Traits;
import net.bodz.bas.trait.spi.ITraitsProvider;

public class BuiltinElementProviderTest
        extends Assert {

    static Set<Class<?>> traitsProviderTypes;
    static {
        traitsProviderTypes = new HashSet<Class<?>>();
        for (ITraitsProvider provider : Traits.getTraitsProviders())
            traitsProviderTypes.add(provider.getClass());
    }

    @Test
    public void testBeanPotatoProviderLoaded() {
        assertTrue(traitsProviderTypes.contains(BeanPotatoElementProvider.class));
    }

    @Test
    public void testReflectPotatoProviderLoaded() {
        assertTrue(traitsProviderTypes.contains(ReflectPotatoElementProvider.class));
    }

}
