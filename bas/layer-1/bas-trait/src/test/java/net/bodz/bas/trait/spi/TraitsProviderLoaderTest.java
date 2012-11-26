package net.bodz.bas.trait.spi;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import net.bodz.bas.trait.Traits;

public class TraitsProviderLoaderTest
        extends Assert {

    static Set<Class<?>> providerClasses;

    @BeforeClass
    public static void setup() {
        providerClasses = new HashSet<Class<?>>();
        for (ITraitsProvider provider : Traits.getTraitsProviders())
            providerClasses.add(provider.getClass());
    }

    @Test
    public void testLoadProvider_Annotation() {
        assertTrue(providerClasses.contains(AnnotationTraitsProvider.class));
    }

    @Test
    public void testLoadProvider_Friends() {
        assertTrue(providerClasses.contains(FriendTraitsProvider.class));
    }

    @Test
    public void testLoadProvider_BasCFriend() {
        assertTrue(providerClasses.contains(BasCompanionTraitsProvider.class));
    }

    @Test
    public void testLoadProvider_QueryMethod() {
        assertTrue(providerClasses.contains(QueryMethodTraitsProvider.class));
    }

    @Test
    public void testLoadProvider_QuerySelf() {
        assertTrue(providerClasses.contains(QuerySelfTraitsProvider.class));
    }

}
