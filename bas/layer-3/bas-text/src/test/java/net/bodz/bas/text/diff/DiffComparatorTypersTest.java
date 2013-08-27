package net.bodz.bas.text.diff;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.typer.Typers;
import net.bodz.bas.typer.std.IInstanceStore;

/**
 * @see DiffComparatorTypers
 */
public class DiffComparatorTypersTest
        extends Assert {

    @Test
    public void testDefaultStoreInstance()
            throws Exception {
        IInstanceStore<? super IDiffComparator> store = Typers.getTyper(IDiffComparator.class, IInstanceStore.class);

        String defaultName = store.getInstanceNames().iterator().next();
        assertEquals("gnudiff", defaultName);
        Object gnudiff = store.getInstance("gnudiff");
        assertEquals(DiffComparators.gnudiff, gnudiff);
    }

}
