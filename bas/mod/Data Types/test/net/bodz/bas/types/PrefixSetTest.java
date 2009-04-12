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
        set.add("name"); //$NON-NLS-1$
        set.add("aa"); //$NON-NLS-1$
        set.add("aaa"); //$NON-NLS-1$
        set.add("aab"); //$NON-NLS-1$
    }

    @Test
    public void testGetChildren() {
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                Iterable<String> ceilings = set.ceilings(input);
                return Strings.join(",", ceilings); //$NON-NLS-1$
            }
        }, //
                EQ("n", "name"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("na", "name"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("nam", "name"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("name", "name"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("a", "aa,aaa,aab"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("aa", "aa,aaa,aab"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("aaa", "aaa"), // //$NON-NLS-1$ //$NON-NLS-2$
                END);
    }

    @Test
    public void testHasChildren() {
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                return set.ceiling(input) != null;
            }
        }, //
                T(""), // //$NON-NLS-1$
                T("n"), // //$NON-NLS-1$
                T("nam"), // //$NON-NLS-1$
                T("name"), // //$NON-NLS-1$
                T("a"), // //$NON-NLS-1$
                T("aa"), // //$NON-NLS-1$
                T("aaa"), // //$NON-NLS-1$
                F("bb"), // //$NON-NLS-1$
                END);
    }

    @Test
    public void testCrossParent() {
        PrefixSet set = new PrefixSet();
        set.add("cat"); //$NON-NLS-1$
        set.add("catx"); //$NON-NLS-1$
        set.add("catz"); //$NON-NLS-1$
        assertEquals("catx", set.floor("catxy")); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("cat", set.floor("caty")); //$NON-NLS-1$ //$NON-NLS-2$
    }

}
