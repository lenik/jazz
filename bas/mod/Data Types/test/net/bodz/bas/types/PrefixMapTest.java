package net.bodz.bas.types;

import static net.bodz.bas.test.TestDefs.END;
import static net.bodz.bas.test.TestDefs.EQ;
import static net.bodz.bas.test.TestDefs.F;
import static net.bodz.bas.test.TestDefs.T;
import net.bodz.bas.test.TestDefs;
import net.bodz.bas.test.TestEval;
import net.bodz.bas.test._TestEval;
import net.bodz.bas.types.util.Strings;

import org.junit.Test;

public class PrefixMapTest {

    PrefixMap<String> map = new PrefixMap<String>();

    public PrefixMapTest() {
        map.put("name", "tom"); //$NON-NLS-1$ //$NON-NLS-2$
        map.put("aa", "aa"); //$NON-NLS-1$ //$NON-NLS-2$
        map.put("aaa", "aaa"); //$NON-NLS-1$ //$NON-NLS-2$
        map.put("aab", "aab"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Test
    public void test_getChildrenValues() {
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                Iterable<String> vals = map.ceilings(input);
                return Strings.join(",", vals); //$NON-NLS-1$
            }
        }, //
                EQ("n", "tom"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("na", "tom"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("nam", "tom"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("name", "tom"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("a", "aa,aaa,aab"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("aa", "aa,aaa,aab"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("aaa", "aaa"), // //$NON-NLS-1$ //$NON-NLS-2$
                END);
    }

    @Test
    public void test_hasChildren() {
        TestDefs.tests(new _TestEval<String>() {
            public Object eval(String input) throws Throwable {
                if (isBreakpoint())
                    System.out.println(input);
                String actu = map.ceilingKey(input);
                return actu != null;
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
    public void test_getParent() {
        final PrefixMap<String> map = new PrefixMap<String>();
        map.put("z.bas", "%bas"); //$NON-NLS-1$ //$NON-NLS-2$
        map.put("z.xml", "%xml"); //$NON-NLS-1$ //$NON-NLS-2$
        TestDefs.tests(new _TestEval<String>() {
            public Object eval(String abbr) throws Throwable {
                if (isBreakpoint())
                    System.err.println(abbr);
                String parent = map.floorKey(abbr);
                if (parent == null)
                    return "==" + abbr; //$NON-NLS-1$
                String expand = map.get(parent);
                abbr = abbr.substring(parent.length());
                return expand + abbr;
            }
        }, //
                EQ("hello", "==hello"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("z.bas", "%bas"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("z.bas123", "%bas123"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("z.bas/hello", "%bas/hello"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("z.ba", "==z.ba"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("z.xml", "%xml"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("z.", "==z."), // //$NON-NLS-1$ //$NON-NLS-2$
                END);
    }

}
