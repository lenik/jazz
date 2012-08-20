package net.bodz.bas.text.diff;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.trait.Traits;
import net.bodz.bas.traits.IInstanceStore;

/**
 * @see DiffComparatorTraits
 */
public class DiffComparatorTraitsTest
        extends Assert {

    @Test
    public void testDefaultStoreInstance()
            throws Exception {
        IInstanceStore<? super DiffComparator> store = Traits.getTrait(DiffComparator.class, IInstanceStore.class);

        String defaultName = store.getInstanceNames().iterator().next();
        assertEquals("gnudiff", defaultName);
        Object gnudiff = store.getInstance("gnudiff");
        assertEquals(DiffComparators.gnudiff, gnudiff);
    }

}
