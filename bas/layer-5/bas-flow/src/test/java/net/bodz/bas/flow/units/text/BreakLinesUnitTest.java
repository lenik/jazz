package net.bodz.bas.flow.units.text;

import java.io.IOException;
import java.util.Collection;

import junit.framework.TestCase;
import net.bodz.bas.flow.units.builtin.text.BreakLinesUnit;
import net.bodz.bas.flow.util.WhatIf;
import net.bodz.bas.string.StringArray;

import org.junit.Test;

public class BreakLinesUnitTest
        extends TestCase {

    BreakLinesUnit chop = new BreakLinesUnit();
    BreakLinesUnit nochop = new BreakLinesUnit();

    public BreakLinesUnitTest() {
        chop.setChop(true);
        nochop.setChop(false);
    }

    @Test
    public void testChopClear()
            throws IOException {
        class D {
            void o(String in, String expected)
                    throws IOException {
                Collection<Object> out = WhatIf.clearSend(chop, in, true);
                String actual = StringArray.join("|", out);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("a", "a");
        d.o("a\n", "a");
        d.o("a\nb", "a|b");
        d.o("a\r\nb", "a|b");
        d.o("a\n\nb\n\rc", "a||b|\rc");
        d.o("a\n\n\n", "a||");
    }

    @Test
    public void testNoChopClear()
            throws IOException {
        class D {
            void o(String in, String expected)
                    throws IOException {
                Collection<Object> out = WhatIf.clearSend(nochop, in, true);
                String actual = StringArray.join("|", out);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("a", "a");
        d.o("a\n", "a\n");
        d.o("a\nb", "a\n|b");
        d.o("a\r\nb", "a\r\n|b");
        d.o("a\n\nb\n\rc", "a\n|\n|b\n|\rc");
        d.o("a\n\n\n", "a\n|\n|\n");
    }

    @Test
    public void testChopCont()
            throws IOException {
        class D {
            void o(String in, String expected)
                    throws IOException {
                Collection<Object> out = WhatIf.send(chop, in);
                String actual = StringArray.join("|", out);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("a", "");
        d.o("a\n", "aa");
        d.o("a\nb", "a");
        d.o("a\r\nb", "ba");
        d.o("a\n\nb\n\rc", "ba||b");
        d.o("a\n\n\n", "\rca||");
    }

    @Test
    public void testNoChopCont()
            throws IOException {
        class D {
            void o(String in, String expected)
                    throws IOException {
                Collection<Object> out = WhatIf.send(nochop, in);
                String actual = StringArray.join("|", out);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("a", "");
        d.o("a\n", "aa\n");
        d.o("a\nb", "a\n");
        d.o("a\r\nb", "ba\r\n");
        d.o("a\n\nb\n\rc", "ba\n|\n|b\n");
        d.o("a\n\n\n", "\rca\n|\n|\n");
    }

}
