package net.bodz.bas.text.diff;

import junit.framework.TestCase;
import net.bodz.bas.traits.ICommonTraits;
import net.bodz.bas.traits.IInstanceStore;
import net.bodz.bas.traits.provider.TypeTraitsResolve;

import org.junit.Test;

/**
 * @see DiffFormatTraits
 */
public class DiffFormatTraitsTest
        extends TestCase {

    @Test
    public void testDefaultStoreInstance()
            throws Exception {
        ICommonTraits<? super DiffFormat> traits = TypeTraitsResolve.findTraits(DiffFormat.class);
        IInstanceStore<? super DiffFormat> store = traits.getInstanceStore();
        Object Simdiff = store.getInstance("Simdiff");
        assertEquals(DiffFormats.Simdiff, Simdiff);
    }

}
