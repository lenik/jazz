package net.bodz.bas.t.variant.conv;

import org.junit.Assert;
import org.junit.Test;

public class StringVarConverterTest
        extends Assert {

    StringVarConverter svc = StringVarConverter.INSTANCE;

    @Test
    public void testNaN() {
        Number NaN = svc.toNumber("NaN");
        assertEquals(NaN, Double.NaN);
    }

    @Test
    public void testInfinity() {
        Number posInf = svc.toNumber("Infinity");
        assertEquals(posInf, Double.POSITIVE_INFINITY);

        Number negInf = svc.toNumber("-Infinity");
        assertEquals(negInf, Double.NEGATIVE_INFINITY);
    }

    @Test
    public void testInt() {
        assertEquals(0L, svc.toNumber("0"));
        assertEquals(-0L, svc.toNumber("-0"));
        assertEquals(123L, svc.toNumber("123"));
        assertEquals(-123L, svc.toNumber("-123"));
    }

    @Test
    public void testDecimal() {
        assertEquals(0.0, svc.toNumber("0.0"));
        assertEquals(-0.0, svc.toNumber("-0.0"));
        assertEquals(123.456, svc.toNumber("123.456"));
        assertEquals(-123.456, svc.toNumber("-123.456"));
    }

    @Test
    public void testComma() {
        assertEquals(12345678L, svc.toNumber("12,345,678"));
        assertEquals(14567.1245, svc.toNumber("1,,4,5,67.12,45"));
    }

}
