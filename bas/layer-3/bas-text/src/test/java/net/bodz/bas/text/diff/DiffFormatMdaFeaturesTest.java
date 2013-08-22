package net.bodz.bas.text.diff;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.mf.MdaFeatures;
import net.bodz.bas.mf.std.IInstanceStore;

/**
 * @see DiffFormatMdaFeatures
 */
public class DiffFormatMdaFeaturesTest
        extends Assert {

    @Test
    public void testDefaultStoreInstance()
            throws Exception {
        IInstanceStore<? super IDiffFormat> diffStore = MdaFeatures.getMdaFeature(IDiffFormat.class, IInstanceStore.class);

        Object Simdiff = diffStore.getInstance("Simdiff");
        assertEquals(IDiffFormat.SIMPLE, Simdiff);
    }

}
