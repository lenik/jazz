package net.bodz.bas.text.diff;

import junit.framework.TestCase;
import net.bodz.bas.traits.IInstanceStore;
import net.bodz.bas.traits.Traits;

import org.junit.Test;

/**
 * @see DiffFormatTraits
 */
public class DiffFormatTraitsTest
        extends TestCase {

    @Test
    public void testDefaultStoreInstance()
            throws Exception {
        @SuppressWarnings("unchecked")
        IInstanceStore<? super DiffFormat> diffStore = Traits.getTraits(DiffFormat.class, IInstanceStore.class);

        Object Simdiff = diffStore.getInstance("Simdiff");
        assertEquals(DiffFormats.Simdiff, Simdiff);
    }

}
