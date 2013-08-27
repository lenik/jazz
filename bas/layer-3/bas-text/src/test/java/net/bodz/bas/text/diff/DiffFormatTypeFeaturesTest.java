package net.bodz.bas.text.diff;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.tf.TypeFeatures;
import net.bodz.bas.tf.std.IInstanceStore;

/**
 * @see DiffFormatTypeFeatures
 */
public class DiffFormatTypeFeaturesTest
        extends Assert {

    @Test
    public void testDefaultStoreInstance()
            throws Exception {
        IInstanceStore<? super IDiffFormat> diffStore = TypeFeatures.getTypeFeature(IDiffFormat.class, IInstanceStore.class);

        Object Simdiff = diffStore.getInstance("Simdiff");
        assertEquals(IDiffFormat.SIMPLE, Simdiff);
    }

}
