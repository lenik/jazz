package net.bodz.bas.text.diff;

import net.bodz.bas.trait.Traits;
import net.bodz.bas.traits.IInstanceStore;

import org.junit.Assert;
import org.junit.Test;

/**
 * @see DiffFormatTraits
 */
public class DiffFormatTraitsTest
        extends Assert {

    @Test
    public void testDefaultStoreInstance()
            throws Exception {
        IInstanceStore<? super DiffFormat> diffStore = Traits.getTrait(DiffFormat.class, IInstanceStore.class);

        Object Simdiff = diffStore.getInstance("Simdiff");
        assertEquals(DiffFormats.Simdiff, Simdiff);
    }

}
