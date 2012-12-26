package net.bodz.bas.i18n.unit.std;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.i18n.unit.Measure;

public class LengthMeasureTest
        extends Assert
        implements LengthUnits {

    @Test
    public void testToString() {
        Measure _3m = new Measure(3, METER);
        // System.out.println(_3m);
        assertEquals("3 m", _3m.toString());
    }

    @Test
    public void testConversion() {
        Measure _3m = new Measure(3, METER);
        Measure result = _3m.in(FOOT);
        assertEquals("9.8425197 ft", result.toString());
    }

}
