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
        map.put("name", "tom");
        map.put("aa", "aa");
        map.put("aaa", "aaa");
        map.put("aab", "aab");
    }

    @Test
    public void test_getChildrenValues() {
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                Iterable<String> vals = map.ceilings(input);
                return Strings.join(",", vals);
            }
        }, //
                EQ("n", "tom"), //
                EQ("na", "tom"), //
                EQ("nam", "tom"), //
                EQ("name", "tom"), //
                EQ("a", "aa,aaa,aab"), //
                EQ("aa", "aa,aaa,aab"), //
                EQ("aaa", "aaa"), //
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
    public void test_getParent() {
        final PrefixMap<String> map = new PrefixMap<String>();
        map.put("z.bas", "%bas");
        map.put("z.xml", "%xml");
        TestDefs.tests(new _TestEval<String>() {
            public Object eval(String abbr) throws Throwable {
                if (isBreakpoint())
                    System.err.println(abbr);
                String parent = map.floorKey(abbr);
                if (parent == null)
                    return "==" + abbr;
                String expand = map.get(parent);
                abbr = abbr.substring(parent.length());
                return expand + abbr;
            }
        }, //
                EQ("hello", "==hello"), //
                EQ("z.bas", "%bas"), //
                EQ("z.bas123", "%bas123"), //
                EQ("z.bas/hello", "%bas/hello"), //
                EQ("z.ba", "==z.ba"), //
                EQ("z.xml", "%xml"), //
                EQ("z.", "==z."), //
                END);
    }

}
