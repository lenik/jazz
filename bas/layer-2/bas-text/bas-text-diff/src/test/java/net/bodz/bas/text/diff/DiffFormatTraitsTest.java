package net.bodz.bas.text.diff;

import static org.junit.Assert.assertEquals;
import net.bodz.bas.type.ITypeTraits;
import net.bodz.bas.type.TypeTraitsResolve;
import net.bodz.bas.type.traits.IInstanceStore;

import org.junit.Test;

public class DiffFormatTraitsTest {

    @Test
    public void testDefaultStoreInstance()
            throws Exception {
        ITypeTraits<? super DiffFormat> traits = TypeTraitsResolve.findTraits(DiffFormat.class);
        IInstanceStore<? super DiffFormat> store = traits.getInstanceStore();
        Object Simdiff = store.getInstance("Simdiff");
        assertEquals(DiffFormats.Simdiff, Simdiff);
    }

}
