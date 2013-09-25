package net.bodz.bas.text.diff;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.typer.Typers;
import net.bodz.bas.typer.std.IInstanceStore;

/**
 * @see DiffFormatTypers
 */
public class DiffFormatTypersTest
        extends Assert {

    @Test
    public void testDefaultStoreInstance()
            throws Exception {
        IInstanceStore<? super IDiffFormat> diffStore = Typers.getTyper(IDiffFormat.class, IInstanceStore.class);

        Object Simdiff = diffStore.getInstance("SIMPLE");
        assertEquals(IDiffFormat.SIMPLE, Simdiff);
    }

}
