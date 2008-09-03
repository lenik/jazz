package net.bodz.bios.units.text;

import static net.bodz.bas.test.TestDefs.END;
import static net.bodz.bas.test.TestDefs.EQ;

import java.util.Collection;

import org.junit.Test;

import net.bodz.bas.test.TestDefs;
import net.bodz.bas.test._TestEval;
import net.bodz.bas.types.util.Strings;
import net.bodz.bios.util.WhatIf;

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
                    out = WhatIf.send(nochop, "", true);
                else
                    out = WhatIf.send(nochop, in);
                return Strings.join("|", out);
            }
        }, //
                EQ("12345", "123"), //
                EQ("67890", "456|789"), //
                EQ("x\n1\n12\n123\n", "0x\n|1\n|12\n|123|\n"), //
                EQ("abcdef", "abc|def"), //
                EQ("ghij", "ghi"),//
                EQ(null, "j"), //
                END);
    }

}
