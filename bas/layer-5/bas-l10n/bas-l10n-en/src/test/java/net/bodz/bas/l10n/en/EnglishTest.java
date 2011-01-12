package net.bodz.bas.l10n.en;

import junit.framework.TestCase;

import org.junit.Test;

public class EnglishTest
        extends TestCase {

    @Test
    public void testPluralOf() {
        class D {
            void o(String input, String expected) {
                String actual = English.pluralOf(input);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("cat", "cats");
        d.o("foot", "foots"); // TODO - irregular plural
        d.o("baby", "babies");
        d.o("homo", "homoes");
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
        d.o("cry", "crying");
        d.o("eat", "eating");
        d.o("hob", "hobbing");
        d.o("go", "going");
        d.o("die", "dying");
        d.o("live", "living");
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
        d.o("eat", "ate");
        // d.o("play", "played"); //
        d.o("late", "lated");
        d.o("hob", "hobbed");
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
        d.o("eat", "eaten");
        // d.o("play", "played"); //
        d.o("late", "lated");
        d.o("hob", "hobbed");
        // d.o("answer", "answered"); //
    }

}
