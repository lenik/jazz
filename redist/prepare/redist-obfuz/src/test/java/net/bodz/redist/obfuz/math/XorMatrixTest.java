package net.bodz.redist.obfuz.math;

import net.bodz.redist.obfuz.pm.XorMatrix;

import org.junit.Test;

public class XorMatrixTest {

    @Test
    public void test1()
            throws Exception {
        XorMatrix a = new XorMatrix(2, 0x12, 0x46, 0x61, 0x33);
        XorMatrix b = new XorMatrix(2, 0xca, 0x31, 0x62, 0x07);
        XorMatrix c = new XorMatrix(2, 0x78, 0x16, 0xd3, 0x99);
        XorMatrix ab = a.multiply(b);
        XorMatrix bc = b.multiply(c);
        XorMatrix ab_c = ab.multiply(c);
        XorMatrix a_bc = a.multiply(bc);
        System.out.println(ab_c);
        System.out.println(a_bc);
    }

}
