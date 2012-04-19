package net.bodz.bas.loader.boot;

import net.bodz.bas.loader.DefaultBooter;
import net.bodz.bas.loader.LoadException;

import org.junit.Assert;
import org.junit.Test;

public class DefaultBooterTest
        extends Assert {

    static int sum = 0;

    public static class TestArgs {
        public static void main(String[] args) {
            for (String arg : args) {
                int n = Integer.parseInt(arg);
                sum += n;
            }
        }
    }

    @Test
    public void testArgs()
            throws LoadException, Throwable {
        String[] args = { "-l", "bodz_icons", "--",//
                TestArgs.class.getName(), "1", "2", };
        sum = 0;
        DefaultBooter.main(args);
        assertEquals(3, sum);
    }

}
