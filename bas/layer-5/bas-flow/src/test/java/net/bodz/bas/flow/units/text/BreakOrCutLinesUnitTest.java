package net.bodz.bas.flow.units.text;

import java.io.IOException;
import java.util.Collection;

import junit.framework.TestCase;
import net.bodz.bas.flow.units.builtin.text.BreakOrCutLinesUnit;
import net.bodz.bas.flow.util.WhatIf;
import net.bodz.bas.string.StringArray;

import org.junit.Test;

public class BreakOrCutLinesUnitTest
        extends TestCase {

    BreakOrCutLinesUnit nochop;
    {
        nochop = new BreakOrCutLinesUnit(3);
        nochop.setChop(false);
    }

    @Test
    public void testNochopCont()
            throws IOException {
        class D {
            void o(String in, String expected)
                    throws IOException {
                Collection<Object> out;
                if (in == null)
                    out = WhatIf.send(nochop, "", true);
                else
                    out = WhatIf.send(nochop, in);
                String actual = StringArray.join("|", out);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("12345", "123");
        d.o("67890", "456|789");
        d.o("x\n1\n12\n123\n", "0x\n|1\n|12\n|123|\n");
        d.o("abcdef", "abc|def");
        d.o("ghij", "ghi");
        d.o(null, "j");
    }

}
