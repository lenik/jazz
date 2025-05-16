package net.bodz.bas.c.java.util;

import java.util.Random;

public class RandomFn {

    public static String digits(Random random, int len, boolean noZeroStart) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            int digit;
            while (true) {
                digit = random.nextInt(10);
                if (i == 0)
                    if (noZeroStart && digit == 0)
                        continue;
                break;
            }
            char ch = (char) ('0' + digit);
            sb.append(ch);
        }
        return sb.toString();
    }

}
