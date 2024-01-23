package net.bodz.bas.crypto.trans;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.crypto.trans.fn.ICodeBin;

public class TimeHashTransientTest
        extends Assert
        implements IFlyingSupport {

    static int timeout = 1000;
    static int slots = 10;
    static int allowAhead = 0;
    static int window = timeout / slots;
    IFlyingTransient trans = new EpochTransient(window)//
            .transform(tr.partialMd5(6, 10));

    @Test
    public void testLastIndexOf()
            throws InterruptedException {
        long time = System.currentTimeMillis();
        ICodeBin snapshot = trans.getCodeAtTime(time);
        long snapshotIndex = time / window;
        for (int i = 0; i < slots; i++) {
            long currentIndex = snapshotIndex + i;
            FlyingIndex fi = trans.lastIndexOf(currentIndex, snapshot, slots, allowAhead);
            assertEquals(snapshotIndex, fi.getIndex());
            assertEquals(-i, snapshotIndex - currentIndex);
        }
        long boundary = snapshotIndex + (slots + 1);
        FlyingIndex fi = trans.lastIndexOf(boundary, snapshot, slots, allowAhead);
        assertFalse(fi.exists());
    }

    public static void main(String[] args)
            throws Exception {
        int window = 1_000;
        IFlyingTransient trans = new EpochTransient(window);
        trans = trans.transform(tr.partialMd5(6, 10));

        long time = System.currentTimeMillis();
        long index = time / window;
        ICodeBin code = trans.getCodeAtTime(time);
        System.out.printf("Current time %d, index %d, code %s.\n", time, index, code);

        for (int i = 0; i < 10; i++) {
            ICodeBin c = trans.getCode(index + i);
            System.out.printf("    future+%d: %s\n", i, c);
        }

        FlyingIndex fi = trans.lastIndexOf(index, "503960", 1000, allowAhead);
        System.out.println("search: " + fi);
    }

}
