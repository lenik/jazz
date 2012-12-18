package net.bodz.bas.c.primitive;

import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Test;

public class IntMathTest
        extends Assert {

    @Test
    public void testMsb() {
        class D {
            void o(int input, int expected) {
                int actual = IntMath.msb(input);
                assertEquals(expected, actual);
            }
        }
        D d = new D();
        d.o(0, 0);
        d.o(0b1, 0b1);
        d.o(0b10, 0b10);
        d.o(0b1100, 0b1000);
        d.o(0b100111000, 0b100000000);
        d.o(0x400a_3c00, 0x4000_0000);
        d.o(0x800a_3c00, 0x8000_0000);
    }

    @Test
    public void testLsb() {
        class D {
            void o(int input, int expected) {
                int actual = IntMath.lsb(input);
                assertEquals(expected, actual);
            }
        }
        D d = new D();
        d.o(0, 0);
        d.o(0b1, 0b1);
        d.o(0b10, 0b10);
        d.o(0b1100, 0b0100);
        d.o(0b100111000, 0b1000);
        d.o(0x400a_3c00, 0x400);
        d.o(0x800a_3c00, 0x400);
    }

    @Test
    public void testOnesInt() {
        class D {
            void o(Integer input, int expected) {
                int actual = IntMath.ones(input);
                assertEquals(expected, actual);
            }
        }
        D d = new D();
        d.o(0, 0);
        d.o(0xFFFFFFFF, 32);
        d.o(0x00000001, 1);
        d.o(0x00101010, 3);
        d.o(0x88888888, 8);
    }

    @Test
    public void testZerosInt() {
        class D {
            void o(Integer input, int expected) {
                int actual = IntMath.zeros(input);
                assertEquals(expected, actual);
            }
        }
        D d = new D();
        d.o(0, 32);
        d.o(0xFFFFFFFF, 0);
        d.o(0x00000001, 31);
        d.o(0x00101010, 29);
        d.o(0x88888888, 24);
    }

    // @Test
    public void generateFactorials() {
        BigInteger n = new BigInteger("1");
        for (int i = 1; i < 100; i++) {
            BigInteger bigI = new BigInteger("" + i);
            n = n.multiply(bigI);
            // System.out.println(i + "! == " + n);
            if (i > 20) {
                System.out.println("new BigInteger(\"" + n + "\"); // " + i);
            } else {
                System.out.println(n + "; // " + i);
            }
        }
    }

    private static final Number[] facs;
    static {
        facs = new Number[] { 1, // 0
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
                new BigInteger("51090942171709440000"), // 21
                new BigInteger("1124000727777607680000"), // 22
                new BigInteger("25852016738884976640000"), // 23
                new BigInteger("620448401733239439360000"), // 24
                new BigInteger("15511210043330985984000000"), // 25
                new BigInteger("403291461126605635584000000"), // 26
                new BigInteger("10888869450418352160768000000"), // 27
                new BigInteger("304888344611713860501504000000"), // 28
                new BigInteger("8841761993739701954543616000000"), // 29
                new BigInteger("265252859812191058636308480000000"), // 30
                new BigInteger("8222838654177922817725562880000000"), // 31
                new BigInteger("263130836933693530167218012160000000"), // 32
                new BigInteger("8683317618811886495518194401280000000"), // 33
                new BigInteger("295232799039604140847618609643520000000"), // 34
                new BigInteger("10333147966386144929666651337523200000000"), // 35
                new BigInteger("371993326789901217467999448150835200000000"), // 36
                new BigInteger("13763753091226345046315979581580902400000000"), // 37
                new BigInteger("523022617466601111760007224100074291200000000"), // 38
                new BigInteger("20397882081197443358640281739902897356800000000"), // 39
                new BigInteger("815915283247897734345611269596115894272000000000"), // 40

                // 41..50
                new BigInteger("33452526613163807108170062053440751665152000000000"), // 41
                new BigInteger("1405006117752879898543142606244511569936384000000000"), // 42
                new BigInteger("60415263063373835637355132068513997507264512000000000"), // 43
                new BigInteger("2658271574788448768043625811014615890319638528000000000"), // 44
                new BigInteger("119622220865480194561963161495657715064383733760000000000"), // 45
                new BigInteger("5502622159812088949850305428800254892961651752960000000000"), // 46
                new BigInteger("258623241511168180642964355153611979969197632389120000000000"), // 47
                new BigInteger("12413915592536072670862289047373375038521486354677760000000000"), // 48
                new BigInteger("608281864034267560872252163321295376887552831379210240000000000"), // 49
                new BigInteger("30414093201713378043612608166064768844377641568960512000000000000"), // 50
        };
    }

    @Test
    public void testFac() {
        for (int i = 0; i <= 50; i++) {
            Number expected = facs[i];
            Number actual = IntMath.fac(i);
            assertEquals(i + "!", expected, actual);
        }
    }

}
