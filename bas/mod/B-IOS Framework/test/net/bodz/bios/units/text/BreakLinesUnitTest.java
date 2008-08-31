package net.bodz.bios.units.text;

import static net.bodz.bas.test.TestDefs.END;
import static net.bodz.bas.test.TestDefs.EQ;

import java.util.Collection;

import net.bodz.bas.test.TestDefs;
import net.bodz.bas.test.TestEval;
import net.bodz.bas.test._TestEval;
import net.bodz.bas.types.util.Strings;
import net.bodz.bios.units.text.BreakLinesUnit;
import net.bodz.bios.util.WhatIf;

import org.junit.Test;

public class BreakLinesUnitTest {

    BreakLinesUnit chop   = new BreakLinesUnit();
    BreakLinesUnit nochop = new BreakLinesUnit();

    public BreakLinesUnitTest() {
        chop.setChop(true);
        nochop.setChop(false);
    }

    @Test
    public void testChopClear() {
        TestDefs.tests(new _TestEval<String>() {
            public Object eval(String in) throws Throwable {
                if (isBreakpoint())
                    System.err.println(in);
                Collection<Object> out = WhatIf.clearSend(chop, in, true);
                return Strings.join("|", out);
            }
        }, //
                EQ("a", "a"), //
                EQ("a\n", "a"), //
                EQ("a\nb", "a|b"), //
                EQ("a\r\nb", "a|b"), //
                EQ("a\n\nb\n\rc", "a||b|\rc"), //
                EQ("a\n\n\n", "a||"), //
                END);
    }

    @Test
    public void testNoChopClear() {
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String in) throws Throwable {
                Collection<Object> out = WhatIf.clearSend(nochop, in, true);
                return Strings.join("|", out);
            }
        }, //
                EQ("a", "a"), //
                EQ("a\n", "a\n"), //
                EQ("a\nb", "a\n|b"), //
                EQ("a\r\nb", "a\r\n|b"), //
                EQ("a\n\nb\n\rc", "a\n|\n|b\n|\rc"), //
                EQ("a\n\n\n", "a\n|\n|\n"), //
                END);
    }

    @Test
    public void testChopCont() {
        TestDefs.tests(new _TestEval<String>() {
            public Object eval(String in) throws Throwable {
                if (isBreakpoint())
                    System.err.println(in);
                Collection<Object> out = WhatIf.send(chop, in);
                return Strings.join("|", out);
            }
        }, //
                EQ("a", ""), //
                EQ("a\n", "aa"), //
                EQ("a\nb", "a"), //
                EQ("a\r\nb", "ba"), //
                EQ("a\n\nb\n\rc", "ba||b"), //
                EQ("a\n\n\n", "\rca||"), //
                END);
    }

    @Test
    public void testNoChopCont() {
        TestDefs.tests(new _TestEval<String>() {
            public Object eval(String in) throws Throwable {
                if (isBreakpoint())
                    System.err.println(in);
                Collection<Object> out = WhatIf.send(nochop, in);
                return Strings.join("|", out);
            }
        }, //
                EQ("a", ""), //
                EQ("a\n", "aa\n"), //
                EQ("a\nb", "a\n"), //
                EQ("a\r\nb", "ba\r\n"), //
                EQ("a\n\nb\n\rc", "ba\n|\n|b\n"), //
                EQ("a\n\n\n", "\rca\n|\n|\n"), //
                END);
    }

}
