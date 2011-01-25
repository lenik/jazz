package net.bodz.bas.las.units;

import java.io.IOException;

import net.bodz.bas.sio.AbstractCharOut;
import net.bodz.bas.sio.Stdio;

import org.junit.Test;

public class PrettyLoggerTest
        extends AbstractCharOut {

    static {
        PrettyLogger logger = new PrettyLogger(null, Stdio.cerr);
        LasUnits.register(PrettyLoggerTest.class, logger);
    }

    @Override
    public void write(char[] chars, int off, int len)
            throws IOException {
        System.out.print(new String(chars, off, len));
        System.out.flush();
    }

    int hello(int age, boolean sex, String loc)
            throws Error {
        if (enter(age, sex, loc))
            try {
                printf("hello, %s. you are %d and live in %s!\n", sex ? "girl" : "boy", age, loc);
                try {
                    say("wonderful");
                } catch (Error e) {
                    println("just fine.");
                }
            } finally {
                leave();
            }
        return 100;
    }

    int say(String s)
            throws Error {
        if (enter(s)) {
            println("Wonderful? No!");
            throw leave(new Error("Not wonderful."));
        }
        return 0;
    }

    @Test
    public void test1() {
        hello(10, false, "a cave");
        hello(20, true, "heaven");
    }

}
