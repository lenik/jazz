package net.bodz.bas.text.diff;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.trait.Traits;
import net.bodz.bas.traits.IInstanceStore;

/**
 * @see DiffFormatTraits
 */
public class DiffFormatTraitsTest
        extends Assert {

    @Test
    public void testDefaultStoreInstance()
            throws Exception {
        IInstanceStore<? super IDiffFormat> diffStore = Traits.getTrait(IDiffFormat.class, IInstanceStore.class);

        Object Simdiff = diffStore.getInstance("Simdiff");
        assertEquals(IDiffFormat.SIMPLE, Simdiff);
    }

}
