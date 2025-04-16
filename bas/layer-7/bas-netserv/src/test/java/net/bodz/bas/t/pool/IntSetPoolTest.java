package net.bodz.bas.t.pool;

import org.junit.Test;

import net.bodz.bas.err.UnderflowException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class IntSetPoolTest {

    @Test
    public void testConstructor() {
        IntSetPool pool = new IntSetPool(1, 10);
        assertEquals(9, pool.capacity()); // Capacity from 1 to 10 is 9
        assertEquals(9, pool.available()); // All numbers are available initially
    }

    @Test
    public void testAllocate()
            throws Exception {
        IntSetPool pool = new IntSetPool(1, 10);
        for (int i = 1; i <= 9; i++) {
            assertEquals(i, pool.allocate().intValue());
            assertEquals(9 - i, pool.available());
        }
        assertThrows(UnderflowException.class, pool::allocate); // No more numbers available
    }

    @Test
    public void testFree()
            throws Exception {
        IntSetPool pool = new IntSetPool(1, 10);
        for (int i = 1; i <= 9; i++) {
            assertEquals(i, pool.allocate().intValue());
        }
        assertTrue(pool.isEmpty());

        int[] toFree = { 3, 7 };
        int nFreed = 0;
        for (int id : toFree) {
            pool.free(id);
            assertFalse(pool.set.contains(id));
            nFreed++;
            assertEquals(nFreed, pool.available()); // Initially 1 available, plus the two nFreed
        }
    }

    @Test
    public void testRewind()
            throws Exception {
        IntSetPool pool = new IntSetPool(1, 10);
        for (int i = 1; i <= 9; i++) {
            assertEquals(i, pool.allocate().intValue());
        }

        int[] toFree = { 3, 7 };
        for (int id : toFree) {
            pool.free(id);
        }

        pool.rewind();
        assertEquals(3, (int) pool.allocate()); // Next available should be 3
    }

    @Test
    public void testConstructorInvalidRange() {
        assertThrows(IllegalArgumentException.class, () -> new IntSetPool(10, 1));
    }

}