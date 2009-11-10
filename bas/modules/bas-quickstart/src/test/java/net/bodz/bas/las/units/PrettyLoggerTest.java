package net.bodz.bas.las.units;

import static net.bodz.bas.las.LasUnits.enter;
import static net.bodz.bas.las.LasUnits.leave;

import java.io.IOException;

import net.bodz.bas.io.CharOut;
import net.bodz.bas.io.CharOuts;
import net.bodz.bas.las.LasUnits;

import org.junit.Test;

public class PrettyLoggerTest extends CharOut {

    static {
        PrettyLogger logger = new PrettyLogger(null, CharOuts.stderr);
        LasUnits.register(PrettyLoggerTest.class, logger);
    }

    @Override
    public void write(char[] chars, int off, int len) throws IOException {
        System.out.print(new String(chars, off, len));
        System.out.flush();
    }

    int hello(int age, boolean sex, String loc) throws Error {
        if (enter(age, sex, loc))
            try {
                printf("hello, %s. you are %d and live in %s!\n", sex ? "girl" //$NON-NLS-1$ //$NON-NLS-2$
                        : "boy", age, loc); //$NON-NLS-1$
                try {
                    say("wonderful"); //$NON-NLS-1$
                } catch (Error e) {
                    println("just fine."); //$NON-NLS-1$
                }
            } finally {
                leave();
            }
        return 100;
    }

    int say(String s) throws Error {
        if (enter(s)) {
            println("Wonderful? No!"); //$NON-NLS-1$
            throw leave(new Error("Not wonderful.")); //$NON-NLS-1$
        }
        return 0;
    }

    @Test
    public void test1() {
        hello(10, false, "a cave"); //$NON-NLS-1$
        hello(20, true, "heaven"); //$NON-NLS-1$
    }

}
