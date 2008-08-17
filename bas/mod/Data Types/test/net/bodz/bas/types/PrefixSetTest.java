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

public class PrefixSetTest {

    PrefixSet set = new PrefixSet();

    public PrefixSetTest() {
        set.add("name");
        set.add("aa");
        set.add("aaa");
        set.add("aab");
    }

    @Test
    public void test_getChildren() {
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                List<String> vals = set.getChildren(input);
                return Strings.join(",", vals);
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
    public void test_hasChildren() {
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                return set.hasChildren(input);
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
