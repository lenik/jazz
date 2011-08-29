package net.bodz.bas.util.primitive;

import net.bodz.bas.util.primitive.ErrorToleranceDouble;

import org.junit.Assert;
import org.junit.Test;

public class ErrorToleranceDoubleTest
        extends Assert {

    @Test
    public void test1() {
        ErrorToleranceDouble a = ErrorToleranceDouble.blur(1.0);

        for (int k = 0; k < 100; k++) {
            for (int i = 1; i < 100; i++)
                a = a.mul(i);
            a = a.log();
        }
        System.out.println(a);
        System.out.println(a.dia());
    }

}
