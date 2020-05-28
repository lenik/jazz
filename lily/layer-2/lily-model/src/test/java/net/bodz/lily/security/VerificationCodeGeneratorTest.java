package net.bodz.lily.security;

import org.junit.Assert;
import org.junit.Test;

public class VerificationCodeGeneratorTest
        extends Assert {

    int window = 300;
    int slots = 3;
    int delta = window / slots;
    VerificationCodeGenerator vcg = new VerificationCodeGenerator(window, slots);

    @Test
    public void testSearchBackwards()
            throws InterruptedException {
        long time = System.currentTimeMillis();
        String code = vcg.generate(time, 10);
        int safeSlots = 1;
        for (int i = 0; i <= (slots + safeSlots); i++) {
            int s = vcg.searchBackwards(time + delta * i, 10, code);
            assertEquals(i, s);
        }
        long boundary = time + delta * (slots + safeSlots + 1);
        int s = vcg.searchBackwards(boundary, 10, code);
        assertEquals(-1, s);
    }

    public static void main1(String[] args)
            throws Exception {
        int window = 100;
        int slots = 3;
        int delta = window / slots;
        VerificationCodeGenerator vcg = new VerificationCodeGenerator(window, slots);
        for (int i = 0; i < slots * 10; i++) {
            long time = System.currentTimeMillis();
            long idx = vcg.getIndex(time);
            String s = vcg.generate(time, 10);
            System.out.println(idx + ": " + s);
            Thread.sleep(delta / 3);
        }
    }

    public static void main(String[] args)
            throws Exception {
        int window = 10000;
        int slots = 10;
        VerificationCodeGenerator vcg = new VerificationCodeGenerator(window, slots);

        long time = System.currentTimeMillis();
        long index = vcg.getIndex(time);
        String code = vcg.generate(time, 10);
        System.out.printf("Current time %d, index %d, code %s.\n", time, index, code);

        for (int i = 0; i < 10; i++) {
            String c = vcg.generateForIndex(index + i, 10);
            System.out.printf("    idx+%d: %s\n", i, c);
        }

        int s = vcg.searchBackwards(time, 10, "927827");
        System.out.println("Last slot: " + s);
    }

}
