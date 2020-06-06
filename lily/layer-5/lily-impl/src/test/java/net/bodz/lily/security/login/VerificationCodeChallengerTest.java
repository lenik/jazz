package net.bodz.lily.security.login;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.lily.security.login.VerificationCodeChallenger;

public class VerificationCodeChallengerTest
        extends Assert {

    int window = 300;
    int slots = VerificationCodeChallenger.NSLOT;
    int delta = window / slots;
    VerificationCodeChallenger vcg = new VerificationCodeChallenger("KeY", window);

    @Test
    public void testRcheck()
            throws InterruptedException {
        long time = System.currentTimeMillis();
        String code = vcg.computeAt(time);
        int safeSlots = 1;
        for (int i = 0; i <= (slots + safeSlots); i++) {
            int s = vcg.rcheckAt(time + delta * i, code);
            assertEquals(i, s);
        }
        long boundary = time + delta * (slots + safeSlots + 1);
        int s = vcg.rcheckAt(boundary, code);
        assertEquals(-1, s);
    }

    public static void main1(String[] args)
            throws Exception {
        int window = 100;
        int slots = 3;
        int delta = window / slots;
        VerificationCodeChallenger vcg = new VerificationCodeChallenger("KeY", window);
        for (int i = 0; i < slots * 10; i++) {
            long time = System.currentTimeMillis();
            long idx = vcg.getIndex(time);
            String s = vcg.computeAt(time);
            System.out.println(idx + ": " + s);
            Thread.sleep(delta / 3);
        }
    }

    public static void main(String[] args)
            throws Exception {
        int window = 10000;
        VerificationCodeChallenger vcg = new VerificationCodeChallenger("KeY", window);

        long time = System.currentTimeMillis();
        long index = vcg.getIndex(time);
        String code = vcg.computeAt(time);
        System.out.printf("Current time %d, index %d, code %s.\n", time, index, code);

        for (int i = 0; i < 10; i++) {
            String c = vcg.mix(index + i);
            System.out.printf("    idx+%d: %s\n", i, c);
        }

        int s = vcg.rcheckAt(time, "491727");
        System.out.println("Last slot: " + s);
    }

}
