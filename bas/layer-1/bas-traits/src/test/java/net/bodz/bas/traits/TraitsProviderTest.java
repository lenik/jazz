package net.bodz.bas.traits;

import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.traits.provider.builtin.AnnotationTraitsProvider;
import net.bodz.bas.traits.provider.builtin.BasFriendTraitsProvider;
import net.bodz.bas.traits.provider.builtin.FriendTraitsProvider;
import net.bodz.bas.traits.provider.builtin.QueryMethodTraitsProvider;
import net.bodz.bas.traits.provider.builtin.QuerySelfTraitsProvider;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TraitsProviderTest
        extends Assert {

    static Set<Class<?>> providerClasses;

    @BeforeClass
    public static void setup() {
        providerClasses = new HashSet<Class<?>>();
        for (ITraitsProvider provider : Traits.traitsProviders)
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
    public void testLoadProvider_BasFriend() {
        assertTrue(providerClasses.contains(BasFriendTraitsProvider.class));
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
