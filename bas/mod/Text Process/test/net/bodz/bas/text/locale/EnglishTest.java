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
                EQ("cat", "cats"), //
                EQ("foot", "foots"), // TODO - irregular plural
                EQ("baby", "babies"), //
                EQ("homo", "homoes"), //
                END);
    }

    @Test
    public void testPresentOf() {
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                return English.presentOf(input);
            }
        }, //
                EQ("cry", "crying"), //
                EQ("eat", "eating"), //
                EQ("hob", "hobbing"), //
                EQ("go", "going"), //
                EQ("die", "dying"), //
                EQ("live", "living"), //
                END);
    }

    @Test
    public void testPastOf() {
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                return English.pastOf(input);
            }
        }, //
                EQ("eat", "ate"), //
                // EQ("play", "played"), //
                EQ("late", "lated"), //
                EQ("hob", "hobbed"), //
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
                EQ("eat", "eaten"), //
                // EQ("play", "played"), //
                EQ("late", "lated"), //
                EQ("hob", "hobbed"), //
                // EQ("answer", "answered"), //
                END);
    }

}
