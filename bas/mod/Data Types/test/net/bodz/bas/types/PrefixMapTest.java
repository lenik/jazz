package net.bodz.bas.types;

import static net.bodz.bas.test.TestDefs.END;
import static net.bodz.bas.test.TestDefs.EQ;
import static net.bodz.bas.test.TestDefs.F;
import static net.bodz.bas.test.TestDefs.T;

import java.util.List;

import net.bodz.bas.test.TestDefs;
import net.bodz.bas.test.TestEval;
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
    public void test_getSubValues() {
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                List<String> vals = map.getSubValues(input);
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
    public void test_hasSub() {
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                return map.hasSub(input);
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

}
