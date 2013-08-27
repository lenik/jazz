package net.bodz.bas.tf.spi;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import net.bodz.bas.tf.TypeFeatures;

public class TypeFeatureProviderLoaderTest
        extends Assert {

    static Set<Class<?>> providerClasses;

    @BeforeClass
    public static void setup() {
        providerClasses = new HashSet<Class<?>>();
        for (ITypeFeatureProvider provider : TypeFeatures.getTypeFeatureProviders())
            providerClasses.add(provider.getClass());
    }

    @Test
    public void testLoadProvider_Annotation() {
        assertTrue(providerClasses.contains(AnnotationTypeFeatureProvider.class));
    }

    @Test
    public void testLoadProvider_Friends() {
        assertTrue(providerClasses.contains(FriendTypeFeatureProvider.class));
    }

    @Test
    public void testLoadProvider_BasCFriend() {
        assertTrue(providerClasses.contains(BasCompanionTypeFeatureProvider.class));
    }

    @Test
    public void testLoadProvider_QueryMethod() {
        assertTrue(providerClasses.contains(QueryMethodTypeFeatureProvider.class));
    }

    @Test
    public void testLoadProvider_QuerySelf() {
        assertTrue(providerClasses.contains(QuerySelfTypeFeatureProvider.class));
    }

}
