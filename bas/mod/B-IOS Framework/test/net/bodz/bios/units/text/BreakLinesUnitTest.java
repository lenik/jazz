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
                return Strings.join("|", out); //$NON-NLS-1$
            }
        }, //
                EQ("a", "a"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("a\n", "a"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("a\nb", "a|b"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("a\r\nb", "a|b"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("a\n\nb\n\rc", "a||b|\rc"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("a\n\n\n", "a||"), // //$NON-NLS-1$ //$NON-NLS-2$
                END);
    }

    @Test
    public void testNoChopClear() {
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String in) throws Throwable {
                Collection<Object> out = WhatIf.clearSend(nochop, in, true);
                return Strings.join("|", out); //$NON-NLS-1$
            }
        }, //
                EQ("a", "a"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("a\n", "a\n"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("a\nb", "a\n|b"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("a\r\nb", "a\r\n|b"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("a\n\nb\n\rc", "a\n|\n|b\n|\rc"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("a\n\n\n", "a\n|\n|\n"), // //$NON-NLS-1$ //$NON-NLS-2$
                END);
    }

    @Test
    public void testChopCont() {
        TestDefs.tests(new _TestEval<String>() {
            public Object eval(String in) throws Throwable {
                if (isBreakpoint())
                    System.err.println(in);
                Collection<Object> out = WhatIf.send(chop, in);
                return Strings.join("|", out); //$NON-NLS-1$
            }
        }, //
                EQ("a", ""), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("a\n", "aa"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("a\nb", "a"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("a\r\nb", "ba"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("a\n\nb\n\rc", "ba||b"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("a\n\n\n", "\rca||"), // //$NON-NLS-1$ //$NON-NLS-2$
                END);
    }

    @Test
    public void testNoChopCont() {
        TestDefs.tests(new _TestEval<String>() {
            public Object eval(String in) throws Throwable {
                if (isBreakpoint())
                    System.err.println(in);
                Collection<Object> out = WhatIf.send(nochop, in);
                return Strings.join("|", out); //$NON-NLS-1$
            }
        }, //
                EQ("a", ""), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("a\n", "aa\n"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("a\nb", "a\n"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("a\r\nb", "ba\r\n"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("a\n\nb\n\rc", "ba\n|\n|b\n"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("a\n\n\n", "\rca\n|\n|\n"), // //$NON-NLS-1$ //$NON-NLS-2$
                END);
    }

}
