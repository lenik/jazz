package net.bodz.bas.ui.css3.property.util;

import org.junit.Assert;
import org.junit.Test;

public class NumberingTest
        extends Assert {

    static String[] conv(int tab, int[] in) {
        String[] out = new String[in.length];
        for (int i = 0; i < in.length; i++) {
            switch (tab) {
            case 0:
                out[i] = Numbering.toLowerLatin(in[i]);
                break;
            case 1:
                out[i] = Numbering.toUpperLatin(in[i]);
                break;
            case 2:
                out[i] = Numbering.toLowerGreek(in[i]);
                break;
            case 3:
                out[i] = Numbering.toUpperGreek(in[i]);
                break;
            }
        }
        return out;
    }

    @Test
    public void testAlphaConverts() {
        int[] tests = { 1, 2, 10, 33, 103, 200 };
        String[] modes = { //
                "lower-latin", //
                "upper-latin", //
                "lower-greek", //
                "upper-greek", //
        };
        String[][] expects = { //
                { "a", "b", "j", "bg", "dy", "hr", }, //
                { "A", "B", "J", "BG", "DY", "HR", }, //
                { "α", "β", "κ", "βι", "εη", "ιθ", }, //
                { "Α", "Β", "Κ", "ΒΙ", "ΕΗ", "ΙΘ", },//
        };

        for (int tab = 0; tab < 4; tab++) {
            String[] results = conv(tab, tests);
            assertArrayEquals(modes[tab], expects[tab], results);
        }
    }

}
