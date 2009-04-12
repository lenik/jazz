package net.bodz.bas.text.locale;

import static net.bodz.bas.test.TestDefs.END;
import static net.bodz.bas.test.TestDefs.EQ;
import net.bodz.bas.test.TestDefs;
import net.bodz.bas.test.TestEval;

import org.junit.Test;

public class EnglishTest {

    @Test
    public void testPluralOf() {
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                return English.pluralOf(input);
            }
        }, //
                EQ("cat", "cats"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("foot", "foots"), // TODO - irregular plural //$NON-NLS-1$ //$NON-NLS-2$
                EQ("baby", "babies"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("homo", "homoes"), // //$NON-NLS-1$ //$NON-NLS-2$
                END);
    }

    @Test
    public void testPresentOf() {
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                return English.presentOf(input);
            }
        }, //
                EQ("cry", "crying"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("eat", "eating"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("hob", "hobbing"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("go", "going"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("die", "dying"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("live", "living"), // //$NON-NLS-1$ //$NON-NLS-2$
                END);
    }

    @Test
    public void testPastOf() {
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                return English.pastOf(input);
            }
        }, //
                EQ("eat", "ate"), // //$NON-NLS-1$ //$NON-NLS-2$
                // EQ("play", "played"), //
                EQ("late", "lated"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("hob", "hobbed"), // //$NON-NLS-1$ //$NON-NLS-2$
                // EQ("answer", "answered"), //
                END);
    }

    @Test
    public void testPerfectOf() {
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                return English.perfectOf(input);
            }
        }, //
                EQ("eat", "eaten"), // //$NON-NLS-1$ //$NON-NLS-2$
                // EQ("play", "played"), //
                EQ("late", "lated"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("hob", "hobbed"), // //$NON-NLS-1$ //$NON-NLS-2$
                // EQ("answer", "answered"), //
                END);
    }

}
