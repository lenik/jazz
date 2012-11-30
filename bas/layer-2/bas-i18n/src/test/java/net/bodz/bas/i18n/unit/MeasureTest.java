package net.bodz.bas.i18n.unit;

import org.junit.Assert;
import org.junit.Test;

public class MeasureTest
        extends Assert
        implements IMeasureUnits {

    @Test
    public void testToString() {
        Measure _3m = new Measure(3, meter);
        // System.out.println(_3m);
        assertEquals("3 m", _3m.toString());
    }

    @Test
    public void testConversion() {
        Measure _3m = new Measure(3, meter);
        Measure result = _3m.convert(foot);
        assertEquals("9.8425197 ft", result.toString());
    }

}
