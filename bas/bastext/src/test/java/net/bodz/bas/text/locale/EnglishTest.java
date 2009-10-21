package net.bodz.bas.text.locale;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EnglishTest {

    @Test
    public void testPluralOf() {
        class D {
            void o(String input, String expected) {
                String actual = English.pluralOf(input);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("cat", "cats"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("foot", "foots"); // TODO - irregular plural //$NON-NLS-1$ //$NON-NLS-2$
        d.o("baby", "babies"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("homo", "homoes"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Test
    public void testPresentOf() {
        class D {
            void o(String input, String expected) {
                String actual = English.presentOf(input);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("cry", "crying"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("eat", "eating"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("hob", "hobbing"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("go", "going"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("die", "dying"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("live", "living"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Test
    public void testPastOf() {
        class D {
            void o(String input, String expected) {
                String actual = English.pastOf(input);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("eat", "ate"); //$NON-NLS-1$ //$NON-NLS-2$
        // d.o("play", "played"); //
        d.o("late", "lated"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("hob", "hobbed"); //$NON-NLS-1$ //$NON-NLS-2$
        // d.o("answer", "answered"); //
    }

    @Test
    public void testPerfectOf() {
        class D {
            void o(String input, String expected) {
                String actual = English.perfectOf(input);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("eat", "eaten"); //$NON-NLS-1$ //$NON-NLS-2$
        // d.o("play", "played"); //
        d.o("late", "lated"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("hob", "hobbed"); //$NON-NLS-1$ //$NON-NLS-2$
        // d.o("answer", "answered"); //
    }

}
