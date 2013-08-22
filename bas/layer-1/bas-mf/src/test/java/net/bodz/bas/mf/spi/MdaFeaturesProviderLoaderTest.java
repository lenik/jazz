package net.bodz.bas.mf.spi;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import net.bodz.bas.mf.MdaFeatures;

public class MdaFeaturesProviderLoaderTest
        extends Assert {

    static Set<Class<?>> providerClasses;

    @BeforeClass
    public static void setup() {
        providerClasses = new HashSet<Class<?>>();
        for (IMdaFeaturesProvider provider : MdaFeatures.getMdaFeaturesProviders())
            providerClasses.add(provider.getClass());
    }

    @Test
    public void testLoadProvider_Annotation() {
        assertTrue(providerClasses.contains(AnnotationMdaFeaturesProvider.class));
    }

    @Test
    public void testLoadProvider_Friends() {
        assertTrue(providerClasses.contains(FriendMdaFeaturesProvider.class));
    }

    @Test
    public void testLoadProvider_BasCFriend() {
        assertTrue(providerClasses.contains(BasCompanionMdaFeaturesProvider.class));
    }

    @Test
    public void testLoadProvider_QueryMethod() {
        assertTrue(providerClasses.contains(QueryMethodMdaFeaturesProvider.class));
    }

    @Test
    public void testLoadProvider_QuerySelf() {
        assertTrue(providerClasses.contains(QuerySelfMdaFeaturesProvider.class));
    }

}
