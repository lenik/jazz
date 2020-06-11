package net.bodz.lily.security.login;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.lily.security.login.key.FlyingIndex;
import net.bodz.lily.security.login.key.SimpleFlyingCode;

public class SimpleFlyingCodeTest
        extends Assert {

    static int timeout = 1000;
    static int slots = 10;
    static int window = timeout / slots;
    SimpleFlyingCode sfc = new SimpleFlyingCode("KeY", window);

    @Test
    public void testLastIndexOf()
            throws InterruptedException {
        long time = System.currentTimeMillis();
        String snapshot = sfc.getCodeAtTime(time);
        long snapshotIndex = time / window;
        for (int i = 0; i < slots; i++) {
            long currentIndex = snapshotIndex + i;
            FlyingIndex fi = sfc.lastIndexOf(currentIndex, snapshot, slots);
            assertEquals(snapshotIndex, fi.getIndex());
            assertEquals(-i, snapshotIndex - currentIndex);
        }
        long boundary = snapshotIndex + (slots + 1);
        FlyingIndex fi = sfc.lastIndexOf(boundary, snapshot, slots);
        assertFalse(fi.exists());
    }

    public static void main1(String[] args)
            throws Exception {
        int timeout = 100;
        int slots = 3;
        int window = timeout / slots;
        SimpleFlyingCode sfc = new SimpleFlyingCode("KeY", window);
        for (int i = 0; i < slots * 10; i++) {
            long time = System.currentTimeMillis();
            long current = time / window;
            String s = sfc.getCodeAtTime(time);
            System.out.println(current + ": " + s);
            Thread.sleep(window / 3);
        }
    }

    public static void main(String[] args)
            throws Exception {
        int window = 1_000;
        SimpleFlyingCode sfc = new SimpleFlyingCode("KeY", window);

        long time = System.currentTimeMillis();
        long index = time / window;
        String code = sfc.getCodeAtTime(time);
        System.out.printf("Current time %d, index %d, code %s.\n", time, index, code);

        for (int i = 0; i < 10; i++) {
            String c = sfc.getCode(index + i);
            System.out.printf("    future+%d: %s\n", i, c);
        }

        FlyingIndex fi = sfc.lastIndexOf(index, "068017", 1000);
        System.out.println("search: " + fi);
    }

}
