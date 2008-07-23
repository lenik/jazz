package net.bodz.bas.types.util;

import static net.bodz.bas.test.TestDefs.END;
import static net.bodz.bas.test.TestDefs.EQ;
import static org.junit.Assert.assertEquals;
import net.bodz.bas.test.TestDefs;
import net.bodz.bas.test.TestEval;

import org.junit.Test;

public class StringsTest {

    @Test
    public void testEllipseStringIntString() {
        String s = "abcdefghijklmnopqrstuvwxyz";
        assertEquals("..", Strings.ellipse(s, 2, "..."));
        assertEquals("a...", Strings.ellipse(s, 4, "..."));
    }

    @Test
    public void testEllipseStringIntStringStringString() {
        String s = "a/b/c/d/e/f/g/h/i";
        assertEquals("a...", Strings.ellipse(s, 4, "...", "/", "/"));
        assertEquals("a/b/...", Strings.ellipse(s, 7, "...", "/", "/"));
        assertEquals("a/b.../i", Strings.ellipse(s, 8, "...", "/", "/"));
        assertEquals("a/b/c.../i", Strings.ellipse(s, 10, "...", "/", "/"));
    }

    @Test
    public void testCount() throws Exception {
        TestDefs.tests("(char pattern)", new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                return Strings.count(input, '.');
            }
        }, //
                EQ("empty", 0), //
                EQ("hello.world", 1), //
                EQ("oh... my god! ", 3), //
                EQ(". dot .. everywhere .", 4) //
                );

        TestDefs.tests("(string pattern /1)", new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                return Strings.count(input, ".");
            }
        }, //
                EQ("empty", 0), //
                EQ("hello.world", 1), //
                EQ("oh... my god! ", 3), //
                EQ(". dot .. everywhere .", 4) //
                );

        TestDefs.tests("(string pattern /2)", new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                return Strings.count(input, "..");
            }
        }, //
                EQ("empty", 0), //
                EQ("hello.world", 0), //
                EQ("oh... my god! ", 1), //
                EQ(". dot .. everywhere .", 1) //
                );
    }

    @Test
    public void testEscape() {
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                return Strings.escape(input);
            }
        }, //
                EQ("", ""), //
                EQ("hello", "hello"), //
                EQ("a\"b\'c", "a\\\"b\\'c"), //
                EQ("a\nb\rc", "a\\nb\\rc"), //
                EQ("a\\b\\\\c\\\\\\d", "a\\\\b\\\\\\\\c\\\\\\\\\\\\d"), //
                END);
    }

}
