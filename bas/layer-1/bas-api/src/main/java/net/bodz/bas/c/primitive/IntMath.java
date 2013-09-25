package net.bodz.bas.c.primitive;

import java.math.BigInteger;

import net.bodz.bas.err.OutOfDomainException;

public class IntMath {

    public static int signum(int n) {
        return n > 0 ? 1 : n < 0 ? -1 : 0;
    }

    public static int signum(long n) {
        return n > 0 ? 1 : n < 0 ? -1 : 0;
    }

    public static int unsign(byte b) {
        return b & 0xFF;
    }

    public static int unsign(short s) {
        return s & 0xFFFF;
    }

    public static long unsign(int i) {
        return i & 0xFFFFFFFFL;
    }

    private static final BigInteger _2e64;
    static {
        _2e64 = new BigInteger("10000000000000000", 16);
    }

    public static BigInteger unsign(long l) {
        BigInteger num = new BigInteger(String.valueOf(l));
        if (l < 0)
            num = num.add(_2e64);
        return num;
    }

    public static int min(int a, long b) {
        if (b > Integer.MAX_VALUE)
            return a;

        int _b = (int) b;
        return (a <= _b) ? a : _b;
    }

    public static int min(long a, int b) {
        if (a > Integer.MAX_VALUE)
            return b;

        int _a = (int) a;
        return (_a <= b) ? _a : b;
    }

    public static int ones(int bits) {
        int c = 0;
        while (bits != 0) {
            bits = bits & (bits - 1);
            c++;
        }
        return c;
    }

    public static int ones(long bits) {
        int c = 0;
        while (bits != 0) {
            bits = bits & (bits - 1);
            c++;
        }
        return c;
    }

    public static int zeros(byte bits) {
        return Byte.SIZE - ones(bits);
    }

    public static int zeros(short bits) {
        return Short.SIZE - ones(bits);
    }

    public static int zeros(int bits) {
        return Integer.SIZE - ones(bits);
    }

    public static int zeros(long bits) {
        return Long.SIZE - ones(bits);
    }

    public static int min2eGreaterOrEqualsTo(int n) {
        int msb = msb(n);
        if (msb == n)
            return msb;
        else
            return msb << 1;
    }

    public static int msb(int n) {
        while (n != 0) {
            int and = n & (n - 1);
            if (and == 0)
                break;
            n = and;
        }
        return n;
    }

    public static long msb(long n) {
        while (n != 0) {
            long and = n & (n - 1);
            if (and == 0)
                break;
            n = and;
        }
        return n;
    }

    public static int lsb(int n) {
        int xor = n ^ (n - 1);
        xor++;
        return xor >> 1;
    }

    public static long lsb(long n) {
        long xor = n ^ (n - 1);
        xor++;
        return xor >> 1;
    }

    public static int reflect(int n, int count) {
        int out = 0;
        while (count-- > 0) {
            out |= n & 1;
            out <<= 1;
            n >>= 1;
        }
        return out;
    }

    static final int H1 = 0b10101010_10101010_10101010_10101010;
    static final int L1 = H1 >> 1;
    static final int H2 = 0b11001100_11001100_11001100_11001100;
    static final int L2 = H2 >> 2;
    static final int H4 = 0b11110000_11110000_11110000_11110000;
    static final int L4 = H4 >> 4;
    static final int H8 = 0b11111111_00000000_11111111_00000000;
    static final int L8 = H8 >> 8;

    public static int reflect8(int n) {
        int x = (n & H4) >>> 4;
        int y = n & L4;
        n = (y << 4) | x;

        x = (n & H2) >>> 2;
        y = n & L2;
        n = (y << 2) | x;

        x = (n & H1) >>> 1;
        y = n & L1;
        n = (y << 1) | x;
        return n;
    }

    public static int reflect16(int n) {
        n = reflect8(n);
        int x = (n & H8) >>> 8;
        int y = n & L8;
        return (y << 8) | x;
    }

    public static int reflect32(int n) {
        n = reflect16(n);
        int x = n >>> 16;
        int y = n & 0xffff;
        return (y << 16) | x;
    }

    private static final Number[] facConsts;
    static {
        facConsts = new Number[] { 1, // 0
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
        /**
         * <pre>
         * new BigInteger(&quot;1124000727777607680000&quot;), // 22
         * new BigInteger(&quot;25852016738884976640000&quot;), // 23
         * new BigInteger(&quot;620448401733239439360000&quot;), // 24
         * new BigInteger(&quot;15511210043330985984000000&quot;), // 25
         * new BigInteger(&quot;403291461126605635584000000&quot;), // 26
         * new BigInteger(&quot;10888869450418352160768000000&quot;), // 27
         * new BigInteger(&quot;304888344611713860501504000000&quot;), // 28
         * new BigInteger(&quot;8841761993739701954543616000000&quot;), // 29
         * new BigInteger(&quot;265252859812191058636308480000000&quot;), // 30
         * new BigInteger(&quot;8222838654177922817725562880000000&quot;), // 31
         * new BigInteger(&quot;263130836933693530167218012160000000&quot;), // 32
         * new BigInteger(&quot;8683317618811886495518194401280000000&quot;), // 33
         * new BigInteger(&quot;295232799039604140847618609643520000000&quot;), // 34
         * new BigInteger(&quot;10333147966386144929666651337523200000000&quot;), // 35
         * new BigInteger(&quot;371993326789901217467999448150835200000000&quot;), // 36
         * new BigInteger(&quot;13763753091226345046315979581580902400000000&quot;), // 37
         * new BigInteger(&quot;523022617466601111760007224100074291200000000&quot;), // 38
         * new BigInteger(&quot;20397882081197443358640281739902897356800000000&quot;), // 39
         * new BigInteger(&quot;815915283247897734345611269596115894272000000000&quot;), // 40
         */
        };
    }

    /**
     * @return factorial
     */
    public static Number fac(int n) {
        if (n < 0)
            throw new OutOfDomainException("n<", n, 0);
        if (n < facConsts.length)
            return facConsts[n];
        int i = facConsts.length; // 41
        BigInteger result = (BigInteger) facConsts[facConsts.length - 1]; // 40!
        while (i <= n) {
            BigInteger bigI = BigInteger.valueOf(i++);
            result = result.multiply(bigI);
        }
        return result;
    }

}
