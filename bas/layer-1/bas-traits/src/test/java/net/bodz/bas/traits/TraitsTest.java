package net.bodz.bas.traits;

import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.traits.provider.ITraitsProvider;
import net.bodz.bas.traits.provider.builtin.AnnotationTraitsProvider;
import net.bodz.bas.traits.provider.builtin.CompanionTraitsProvider;
import net.bodz.bas.traits.provider.builtin.GetTraitsMethodTraitsProvider;
import net.bodz.bas.traits.provider.builtin.ImmediateCastTraitsProvider;

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
        assertTrue(providerClasses.contains(CompanionTraitsProvider.class));
        assertTrue(providerClasses.contains(GetTraitsMethodTraitsProvider.class));
        assertTrue(providerClasses.contains(ImmediateCastTraitsProvider.class));
    }

}
