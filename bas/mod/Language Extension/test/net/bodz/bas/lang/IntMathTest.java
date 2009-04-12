package net.bodz.bas.lang;

import static net.bodz.bas.test.TestDefs.END;
import static net.bodz.bas.test.TestDefs.EQ;
import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import net.bodz.bas.test.TestDefs;
import net.bodz.bas.test.TestEval;

import org.junit.Test;

public class IntMathTest {

    @Test
    public void testOnesInt() {
        TestDefs.tests(new TestEval<Integer>() {
            public Object eval(Integer input) throws Throwable {
                return IntMath.ones(input);
            }
        }, //
                EQ(0, 0), //
                EQ(0xFFFFFFFF, 32), //
                EQ(0x00000001, 1), //
                EQ(0x00101010, 3), //
                EQ(0x88888888, 8), //
                END);
    }

    @Test
    public void testZerosInt() {
        TestDefs.tests(new TestEval<Integer>() {
            public Object eval(Integer input) throws Throwable {
                return IntMath.zeros(input);
            }
        }, //
                EQ(0, 32), //
                EQ(0xFFFFFFFF, 0), //
                EQ(0x00000001, 31), //
                EQ(0x00101010, 29), //
                EQ(0x88888888, 24), //
                END);
    }

    // @Test
    public void generateFactorials() {
        BigInteger n = new BigInteger("1"); //$NON-NLS-1$
        for (int i = 1; i < 100; i++) {
            BigInteger bigI = new BigInteger("" + i); //$NON-NLS-1$
            n = n.multiply(bigI);
            // System.out.println(i + "! == " + n);
            if (i > 20) {
                System.out.println("new BigInteger(\"" + n + "\"), // " + i); //$NON-NLS-1$ //$NON-NLS-2$
            } else {
                System.out.println(n + ", // " + i); //$NON-NLS-1$
            }
        }
    }

    private static final Number[] facs;
    static {
        facs = new Number[] {
            1, // 0
            1, // 1
            2, // 2
            6, // 3
            24, // 4
            120, // 5
            720, // 6
            5040, // 7
            40320, // 8
            362880, // 9
            3628800, // 10
            39916800, // 11
            479001600, // 12
            6227020800L, // 13
            87178291200L, // 14
            1307674368000L, // 15
            20922789888000L, // 16
            355687428096000L, // 17
            6402373705728000L, // 18
            121645100408832000L, // 19
            2432902008176640000L, // 20
            new BigInteger("51090942171709440000"), // 21 //$NON-NLS-1$
            new BigInteger("1124000727777607680000"), // 22 //$NON-NLS-1$
            new BigInteger("25852016738884976640000"), // 23 //$NON-NLS-1$
            new BigInteger("620448401733239439360000"), // 24 //$NON-NLS-1$
            new BigInteger("15511210043330985984000000"), // 25 //$NON-NLS-1$
            new BigInteger("403291461126605635584000000"), // 26 //$NON-NLS-1$
            new BigInteger("10888869450418352160768000000"), // 27 //$NON-NLS-1$
            new BigInteger("304888344611713860501504000000"), // 28 //$NON-NLS-1$
            new BigInteger("8841761993739701954543616000000"), // 29 //$NON-NLS-1$
            new BigInteger("265252859812191058636308480000000"), // 30 //$NON-NLS-1$
            new BigInteger("8222838654177922817725562880000000"), // 31 //$NON-NLS-1$
            new BigInteger("263130836933693530167218012160000000"), // 32 //$NON-NLS-1$
            new BigInteger("8683317618811886495518194401280000000"), // 33 //$NON-NLS-1$
            new BigInteger("295232799039604140847618609643520000000"), // 34 //$NON-NLS-1$
            new BigInteger("10333147966386144929666651337523200000000"), // 35 //$NON-NLS-1$
            new BigInteger("371993326789901217467999448150835200000000"), // 36 //$NON-NLS-1$
            new BigInteger("13763753091226345046315979581580902400000000"), // 37 //$NON-NLS-1$
            new BigInteger("523022617466601111760007224100074291200000000"), // 38 //$NON-NLS-1$
            new BigInteger("20397882081197443358640281739902897356800000000"), // 39 //$NON-NLS-1$
            new BigInteger("815915283247897734345611269596115894272000000000"), // 40 //$NON-NLS-1$

            // 41..50
            new BigInteger("33452526613163807108170062053440751665152000000000"), // 41 //$NON-NLS-1$
            new BigInteger(
                    "1405006117752879898543142606244511569936384000000000"), // 42 //$NON-NLS-1$
            new BigInteger(
                    "60415263063373835637355132068513997507264512000000000"), // 43 //$NON-NLS-1$
            new BigInteger(
                    "2658271574788448768043625811014615890319638528000000000"), // 44 //$NON-NLS-1$
            new BigInteger(
                    "119622220865480194561963161495657715064383733760000000000"), // 45 //$NON-NLS-1$
            new BigInteger(
                    "5502622159812088949850305428800254892961651752960000000000"), // 46 //$NON-NLS-1$
            new BigInteger(
                    "258623241511168180642964355153611979969197632389120000000000"), // 47 //$NON-NLS-1$
            new BigInteger(
                    "12413915592536072670862289047373375038521486354677760000000000"), // 48 //$NON-NLS-1$
            new BigInteger(
                    "608281864034267560872252163321295376887552831379210240000000000"), // 49 //$NON-NLS-1$
            new BigInteger(
                    "30414093201713378043612608166064768844377641568960512000000000000"), // 50 //$NON-NLS-1$
        };
    }

    @Test
    public void testFac() {
        for (int i = 0; i <= 50; i++) {
            Number expected = facs[i];
            Number actual = IntMath.fac(i);
            assertEquals(i + "!", expected, actual); //$NON-NLS-1$
        }
    }

}
