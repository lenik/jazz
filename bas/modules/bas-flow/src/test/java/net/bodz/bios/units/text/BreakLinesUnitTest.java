package net.bodz.bios.units.text;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Collection;

import net.bodz.bas.text.util.Strings;
import net.bodz.bios.util.WhatIf;

import org.junit.Test;

public class BreakLinesUnitTest {

    BreakLinesUnit chop = new BreakLinesUnit();
    BreakLinesUnit nochop = new BreakLinesUnit();

    public BreakLinesUnitTest() {
        chop.setChop(true);
        nochop.setChop(false);
    }

    @Test
    public void testChopClear() throws IOException {
        class D {
            void o(String in, String expected) throws IOException {
                Collection<Object> out = WhatIf.clearSend(chop, in, true);
                String actual = Strings.join("|", out); //$NON-NLS-1$
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("a", "a"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("a\n", "a"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("a\nb", "a|b"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("a\r\nb", "a|b"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("a\n\nb\n\rc", "a||b|\rc"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("a\n\n\n", "a||"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Test
    public void testNoChopClear() throws IOException {
        class D {
            void o(String in, String expected) throws IOException {
                Collection<Object> out = WhatIf.clearSend(nochop, in, true);
                String actual = Strings.join("|", out); //$NON-NLS-1$
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("a", "a"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("a\n", "a\n"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("a\nb", "a\n|b"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("a\r\nb", "a\r\n|b"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("a\n\nb\n\rc", "a\n|\n|b\n|\rc"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("a\n\n\n", "a\n|\n|\n"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Test
    public void testChopCont() throws IOException {
        class D {
            void o(String in, String expected) throws IOException {
                Collection<Object> out = WhatIf.send(chop, in);
                String actual = Strings.join("|", out); //$NON-NLS-1$
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("a", ""); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("a\n", "aa"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("a\nb", "a"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("a\r\nb", "ba"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("a\n\nb\n\rc", "ba||b"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("a\n\n\n", "\rca||"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Test
    public void testNoChopCont() throws IOException {
        class D {
            void o(String in, String expected) throws IOException {
                Collection<Object> out = WhatIf.send(nochop, in);
                String actual = Strings.join("|", out); //$NON-NLS-1$
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("a", ""); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("a\n", "aa\n"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("a\nb", "a\n"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("a\r\nb", "ba\r\n"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("a\n\nb\n\rc", "ba\n|\n|b\n"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("a\n\n\n", "\rca\n|\n|\n"); //$NON-NLS-1$ //$NON-NLS-2$
    }

}
