package net.bodz.bas.text.encodings;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LookupsTest {

    @Test
    public void testC2N() {
        String s = "abcdefghijklmnopqrstuvwxyz";
        for (int t = 0; t < 2; t++) {
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                int n = Lookups.c2n[c];
                assertEquals(n, 10 + i);
            }
            s = s.toUpperCase();
        }
    }

}
