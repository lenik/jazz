package net.bodz.bas.sio.util;

import org.junit.Assert;
import org.junit.Test;

public class TextIndentionTest
        extends Assert {

    @Test(expected = IllegalStateException.class)
    public void testUnindentTooMuch() {
        TextIndention t = new TextIndention();
        t.decreaseIndentLevel();
    }

    @Test
    public void testIndentSize3() {
        TextIndention t = new TextIndention();
        assertEquals("", t.getCurrentLinePrefix());

        t.setIndentSize(3);
        t.increaseIndentLevel();
        assertEquals("   ", t.getCurrentLinePrefix());
        t.increaseIndentLevel();
        assertEquals("      ", t.getCurrentLinePrefix());
        t.increaseIndentLevel();
        assertEquals("         ", t.getCurrentLinePrefix());
        t.increaseIndentLevel();
        assertEquals("            ", t.getCurrentLinePrefix());
        t.increaseIndentLevel();
        assertEquals("               ", t.getCurrentLinePrefix());
        t.decreaseIndentLevel();
        assertEquals("            ", t.getCurrentLinePrefix());
        t.decreaseIndentLevel();
        assertEquals("         ", t.getCurrentLinePrefix());
        t.decreaseIndentLevel();
        assertEquals("      ", t.getCurrentLinePrefix());
        t.decreaseIndentLevel();
        assertEquals("   ", t.getCurrentLinePrefix());
        t.decreaseIndentLevel();
        assertEquals("", t.getCurrentLinePrefix());
    }

    @Test
    public void testIndentSize4() {
        TextIndention t = new TextIndention();
        assertEquals("", t.getCurrentLinePrefix());

        t.setIndentSize(4);
        t.increaseIndentLevel();
        assertEquals("    ", t.getCurrentLinePrefix());
        t.increaseIndentLevel();
        assertEquals("        ", t.getCurrentLinePrefix());
        t.increaseIndentLevel();
        assertEquals("            ", t.getCurrentLinePrefix());
        t.increaseIndentLevel();
        assertEquals("                ", t.getCurrentLinePrefix());
        t.increaseIndentLevel();
        assertEquals("                    ", t.getCurrentLinePrefix());
        t.decreaseIndentLevel();
        assertEquals("                ", t.getCurrentLinePrefix());
        t.decreaseIndentLevel();
        assertEquals("            ", t.getCurrentLinePrefix());
        t.decreaseIndentLevel();
        assertEquals("        ", t.getCurrentLinePrefix());
        t.decreaseIndentLevel();
        assertEquals("    ", t.getCurrentLinePrefix());
        t.decreaseIndentLevel();
        assertEquals("", t.getCurrentLinePrefix());
    }

    @Test
    public void testTabSize3() {
        TextIndention t = new TextIndention();
        assertEquals("", t.getCurrentLinePrefix());

        t.setTabSize(3);
        t.increaseIndentLevel();
        assertEquals("\t", t.getCurrentLinePrefix());
        t.increaseIndentLevel();
        assertEquals("\t\t", t.getCurrentLinePrefix());
        t.increaseIndentLevel();
        assertEquals("\t\t\t", t.getCurrentLinePrefix());
        t.increaseIndentLevel();
        assertEquals("\t\t\t\t", t.getCurrentLinePrefix());
        t.increaseIndentLevel();
        assertEquals("\t\t\t\t\t", t.getCurrentLinePrefix());
        t.decreaseIndentLevel();
        assertEquals("\t\t\t\t", t.getCurrentLinePrefix());
        t.decreaseIndentLevel();
        assertEquals("\t\t\t", t.getCurrentLinePrefix());
        t.decreaseIndentLevel();
        assertEquals("\t\t", t.getCurrentLinePrefix());
        t.decreaseIndentLevel();
        assertEquals("\t", t.getCurrentLinePrefix());
        t.decreaseIndentLevel();
        assertEquals("", t.getCurrentLinePrefix());
    }

    @Test
    public void testTabSize4() {
        TextIndention t = new TextIndention();
        assertEquals("", t.getCurrentLinePrefix());

        t.setTabSize(4);
        t.increaseIndentLevel();
        assertEquals("\t", t.getCurrentLinePrefix());
        t.increaseIndentLevel();
        assertEquals("\t\t", t.getCurrentLinePrefix());
        t.increaseIndentLevel();
        assertEquals("\t\t\t", t.getCurrentLinePrefix());
        t.increaseIndentLevel();
        assertEquals("\t\t\t\t", t.getCurrentLinePrefix());
        t.increaseIndentLevel();
        assertEquals("\t\t\t\t\t", t.getCurrentLinePrefix());
        t.decreaseIndentLevel();
        assertEquals("\t\t\t\t", t.getCurrentLinePrefix());
        t.decreaseIndentLevel();
        assertEquals("\t\t\t", t.getCurrentLinePrefix());
        t.decreaseIndentLevel();
        assertEquals("\t\t", t.getCurrentLinePrefix());
        t.decreaseIndentLevel();
        assertEquals("\t", t.getCurrentLinePrefix());
        t.decreaseIndentLevel();
        assertEquals("", t.getCurrentLinePrefix());
    }

    @Test
    public void testIndent3Tab4() {
        TextIndention t = new TextIndention();
        assertEquals("", t.getCurrentLinePrefix());

        t.setIndentSize(3);
        t.setTabSize(4);
        t.setMixedMode(true);

        t.increaseIndentLevel();
        assertEquals("   ", t.getCurrentLinePrefix());
        t.increaseIndentLevel();
        assertEquals("\t  ", t.getCurrentLinePrefix());
        t.increaseIndentLevel();
        assertEquals("\t\t ", t.getCurrentLinePrefix());
        t.increaseIndentLevel();
        assertEquals("\t\t\t", t.getCurrentLinePrefix());
        t.increaseIndentLevel();
        assertEquals("\t\t\t   ", t.getCurrentLinePrefix());
        t.decreaseIndentLevel();
        assertEquals("\t\t\t", t.getCurrentLinePrefix());
        t.decreaseIndentLevel();
        assertEquals("\t\t ", t.getCurrentLinePrefix());
        t.decreaseIndentLevel();
        assertEquals("\t  ", t.getCurrentLinePrefix());
        t.decreaseIndentLevel();
        assertEquals("   ", t.getCurrentLinePrefix());
        t.decreaseIndentLevel();
        assertEquals("", t.getCurrentLinePrefix());
    }

    @Test
    public void testIndent4Tab4() {
        TextIndention t = new TextIndention();
        assertEquals("", t.getCurrentLinePrefix());

        t.setIndentSize(4);
        t.setTabSize(4);
        t.setMixedMode(true);

        t.increaseIndentLevel();
        assertEquals("\t", t.getCurrentLinePrefix());
        t.increaseIndentLevel();
        assertEquals("\t\t", t.getCurrentLinePrefix());
        t.increaseIndentLevel();
        assertEquals("\t\t\t", t.getCurrentLinePrefix());
        t.increaseIndentLevel();
        assertEquals("\t\t\t\t", t.getCurrentLinePrefix());
        t.increaseIndentLevel();
        assertEquals("\t\t\t\t\t", t.getCurrentLinePrefix());
        t.decreaseIndentLevel();
        assertEquals("\t\t\t\t", t.getCurrentLinePrefix());
        t.decreaseIndentLevel();
        assertEquals("\t\t\t", t.getCurrentLinePrefix());
        t.decreaseIndentLevel();
        assertEquals("\t\t", t.getCurrentLinePrefix());
        t.decreaseIndentLevel();
        assertEquals("\t", t.getCurrentLinePrefix());
        t.decreaseIndentLevel();
        assertEquals("", t.getCurrentLinePrefix());
    }

    @Test
    public void testIndent4Tab3() {
        TextIndention t = new TextIndention();
        assertEquals("", t.getCurrentLinePrefix());

        t.setIndentSize(4);
        t.setTabSize(3);
        t.setMixedMode(true);

        t.increaseIndentLevel();
        assertEquals("\t ", t.getCurrentLinePrefix());
        t.increaseIndentLevel();
        assertEquals("\t\t  ", t.getCurrentLinePrefix());
        t.increaseIndentLevel();
        assertEquals("\t\t\t\t", t.getCurrentLinePrefix());
        t.increaseIndentLevel();
        assertEquals("\t\t\t\t\t ", t.getCurrentLinePrefix());
        t.increaseIndentLevel();
        assertEquals("\t\t\t\t\t\t  ", t.getCurrentLinePrefix());
        t.decreaseIndentLevel();
        assertEquals("\t\t\t\t\t ", t.getCurrentLinePrefix());
        t.decreaseIndentLevel();
        assertEquals("\t\t\t\t", t.getCurrentLinePrefix());
        t.decreaseIndentLevel();
        assertEquals("\t\t  ", t.getCurrentLinePrefix());
        t.decreaseIndentLevel();
        assertEquals("\t ", t.getCurrentLinePrefix());
        t.decreaseIndentLevel();
        assertEquals("", t.getCurrentLinePrefix());
    }

}
