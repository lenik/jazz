package net.bodz.bas.text.diff;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.mf.MdaFeatures;
import net.bodz.bas.mf.std.IInstanceStore;

/**
 * @see DiffComparatorMdaFeatures
 */
public class DiffComparatorMdaFeaturesTest
        extends Assert {

    @Test
    public void testDefaultStoreInstance()
            throws Exception {
        IInstanceStore<? super IDiffComparator> store = MdaFeatures.getMdaFeature(IDiffComparator.class, IInstanceStore.class);

        String defaultName = store.getInstanceNames().iterator().next();
        assertEquals("gnudiff", defaultName);
        Object gnudiff = store.getInstance("gnudiff");
        assertEquals(DiffComparators.gnudiff, gnudiff);
    }

}
