package net.bodz.bas.typer.spi;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import net.bodz.bas.typer.Typers;

public class TyperProviderLoaderTest
        extends Assert {

    static Set<Class<?>> providerClasses;

    @BeforeClass
    public static void setup() {
        providerClasses = new HashSet<Class<?>>();
        for (ITyperProvider provider : Typers.getTyperProviders())
            providerClasses.add(provider.getClass());
    }

    @Test
    public void testLoadProvider_Annotation() {
        assertTrue(providerClasses.contains(AnnotationTyperProvider.class));
    }

    @Test
    public void testLoadProvider_Friends() {
        assertTrue(providerClasses.contains(FriendTyperProvider.class));
    }

    @Test
    public void testLoadProvider_BasCFriend() {
        assertTrue(providerClasses.contains(BasCompanionTyperProvider.class));
    }

    @Test
    public void testLoadProvider_QueryMethod() {
        assertTrue(providerClasses.contains(QueryMethodTyperProvider.class));
    }

    @Test
    public void testLoadProvider_QuerySelf() {
        assertTrue(providerClasses.contains(QuerySelfTyperProvider.class));
    }

}
