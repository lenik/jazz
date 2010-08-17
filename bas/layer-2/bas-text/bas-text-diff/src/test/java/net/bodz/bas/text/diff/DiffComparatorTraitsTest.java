package net.bodz.bas.text.diff;

import junit.framework.TestCase;
import net.bodz.bas.type.ITypeTraits;
import net.bodz.bas.type.TypeTraitsResolve;
import net.bodz.bas.type.traits.IInstanceStore;

import org.junit.Test;

/**
 * @see DiffComparatorTraits
 */
public class DiffComparatorTraitsTest
        extends TestCase {

    @Test
    public void testDefaultStoreInstance()
            throws Exception {
        ITypeTraits<? super DiffComparator> traits = TypeTraitsResolve.findTraits(DiffComparator.class);
        IInstanceStore<? super DiffComparator> store = traits.getInstanceStore();
        String defaultName = store.getInstanceNames().iterator().next();
        assertEquals("gnudiff", defaultName);
        Object gnudiff = store.getInstance("gnudiff");
        assertEquals(DiffComparators.gnudiff, gnudiff);
    }

}
