package net.bodz.bas.text.diff;

import net.bodz.bas.traits.IInstanceStore;
import net.bodz.bas.traits.Traits;

import org.junit.Assert;
import org.junit.Test;

/**
 * @see DiffComparatorTraits
 */
public class DiffComparatorTraitsTest
        extends Assert {

    @Test
    public void testDefaultStoreInstance()
            throws Exception {
        @SuppressWarnings("unchecked")
        IInstanceStore<? super DiffComparator> store = Traits.getTraits(DiffComparator.class, IInstanceStore.class);

        String defaultName = store.getInstanceNames().iterator().next();
        assertEquals("gnudiff", defaultName);
        Object gnudiff = store.getInstance("gnudiff");
        assertEquals(DiffComparators.gnudiff, gnudiff);
    }

}
