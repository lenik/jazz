package net.bodz.bas.text.diff;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.tf.TypeFeatures;
import net.bodz.bas.tf.std.IInstanceStore;

/**
 * @see DiffComparatorTypeFeatures
 */
public class DiffComparatorTypeFeaturesTest
        extends Assert {

    @Test
    public void testDefaultStoreInstance()
            throws Exception {
        IInstanceStore<? super IDiffComparator> store = TypeFeatures.getTypeFeature(IDiffComparator.class, IInstanceStore.class);

        String defaultName = store.getInstanceNames().iterator().next();
        assertEquals("gnudiff", defaultName);
        Object gnudiff = store.getInstance("gnudiff");
        assertEquals(DiffComparators.gnudiff, gnudiff);
    }

}
