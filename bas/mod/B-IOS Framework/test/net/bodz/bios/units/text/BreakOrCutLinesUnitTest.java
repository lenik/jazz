package net.bodz.bios.units.text;

import static net.bodz.bas.test.TestDefs.END;
import static net.bodz.bas.test.TestDefs.EQ;

import java.util.Collection;

import net.bodz.bas.test.TestDefs;
import net.bodz.bas.test._TestEval;
import net.bodz.bas.types.util.Strings;
import net.bodz.bios.util.WhatIf;

import org.junit.Test;

public class BreakOrCutLinesUnitTest {

    BreakOrCutLinesUnit nochop;
    {
        nochop = new BreakOrCutLinesUnit(3);
        nochop.setChop(false);
    }

    @Test
    public void testNochopCont() {
        TestDefs.tests(new _TestEval<String>() {
            public Object eval(String in) throws Throwable {
                if (isBreakpoint())
                    System.err.println(in);
                Collection<Object> out;
                if (in == null)
                    out = WhatIf.send(nochop, "", true); //$NON-NLS-1$
                else
                    out = WhatIf.send(nochop, in);
                return Strings.join("|", out); //$NON-NLS-1$
            }
        }, //
                EQ("12345", "123"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("67890", "456|789"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("x\n1\n12\n123\n", "0x\n|1\n|12\n|123|\n"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("abcdef", "abc|def"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("ghij", "ghi"),// //$NON-NLS-1$ //$NON-NLS-2$
                EQ(null, "j"), // //$NON-NLS-1$
                END);
    }

}
