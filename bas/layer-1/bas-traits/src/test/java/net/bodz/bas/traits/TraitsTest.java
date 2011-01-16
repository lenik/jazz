package net.bodz.bas.traits;

import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.traits.provider.builtin.AnnotationTraitsProvider;
import net.bodz.bas.traits.provider.builtin.BasFriendTraitsProvider;
import net.bodz.bas.traits.provider.builtin.FriendTraitsProvider;
import net.bodz.bas.traits.provider.builtin.GetTraitsMethodTraitsProvider;
import net.bodz.bas.traits.provider.builtin.ImmediateQueryTraitsProvider;

import org.junit.Assert;
import org.junit.Test;

public class TraitsTest
        extends Assert {

    @Test
    public void testLoadProviders() {
        Set<Class<?>> providerClasses = new HashSet<Class<?>>();
        for (ITraitsProvider provider : Traits.traitsProviders)
            providerClasses.add(provider.getClass());
        assertTrue(providerClasses.contains(AnnotationTraitsProvider.class));
        assertTrue(providerClasses.contains(FriendTraitsProvider.class));
        assertTrue(providerClasses.contains(BasFriendTraitsProvider.class));
        assertTrue(providerClasses.contains(GetTraitsMethodTraitsProvider.class));
        assertTrue(providerClasses.contains(ImmediateQueryTraitsProvider.class));
    }

}
