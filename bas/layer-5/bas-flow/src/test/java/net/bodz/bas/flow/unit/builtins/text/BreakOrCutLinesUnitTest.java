package net.bodz.bas.flow.unit.builtins.text;

import java.io.IOException;
import java.util.Collection;

import net.bodz.bas.c.string.StringArray;
import net.bodz.bas.flow.unit.builtins.text.BreakOrCutLinesUnit;
import net.bodz.bas.flow.utils.WhatIf;

import org.junit.Assert;
import org.junit.Test;

public class BreakOrCutLinesUnitTest
        extends Assert {

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
