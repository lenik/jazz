package net.bodz.bas.types;

import static net.bodz.bas.test.TestDefs.END;
import static net.bodz.bas.test.TestDefs.EQ;
import static net.bodz.bas.test.TestDefs.F;
import static net.bodz.bas.test.TestDefs.T;
import static org.junit.Assert.assertEquals;
import net.bodz.bas.test.TestDefs;
import net.bodz.bas.test.TestEval;
import net.bodz.bas.types.util.Strings;

import org.junit.Test;

public class PrefixSetTest {

    PrefixSet set = new PrefixSet();

    public PrefixSetTest() {
        set.add("name");
        set.add("aa");
        set.add("aaa");
        set.add("aab");
    }

    @Test
    public void testGetChildren() {
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                Iterable<String> ceilings = set.ceilings(input);
                return Strings.join(",", ceilings);
            }
        }, //
                EQ("n", "name"), //
                EQ("na", "name"), //
                EQ("nam", "name"), //
                EQ("name", "name"), //
                EQ("a", "aa,aaa,aab"), //
                EQ("aa", "aa,aaa,aab"), //
                EQ("aaa", "aaa"), //
                END);
    }

    @Test
    public void testHasChildren() {
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                return set.ceiling(input) != null;
            }
        }, //
                T(""), //
                T("n"), //
                T("nam"), //
                T("name"), //
                T("a"), //
                T("aa"), //
                T("aaa"), //
                F("bb"), //
                END);
    }

    @Test
    public void testCrossParent() {
        PrefixSet set = new PrefixSet();
        set.add("cat");
        set.add("catx");
        set.add("catz");
        assertEquals("catx", set.floor("catxy"));
        assertEquals("cat", set.floor("caty"));
    }

}
