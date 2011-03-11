package net.bodz.bas.collection.preorder;

import org.junit.Assert;
import org.junit.Test;

public class PrefixSetTest
        extends Assert {

    PrefixSet set = new PrefixSet();

    public PrefixSetTest() {
        set.add("name");
        set.add("aa");
        set.add("aaa");
        set.add("aab");
    }

    static String join(String delim, Iterable<?> vals) {
        StringBuilder buf = null;
        for (Object val : vals) {
            if (buf == null)
                buf = new StringBuilder();
            else
                buf.append(delim);
            buf.append(val);
        }
        return buf.toString();
    }

    @Test
    public void testGetChildren() {
        class D {
            void o(String input, String expected) {
                Iterable<String> ceilings = set.ceilings(input);
                String actual = join(",", ceilings);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("n", "name");
        d.o("na", "name");
        d.o("nam", "name");
        d.o("name", "name");
        d.o("a", "aa,aaa,aab");
        d.o("aa", "aa,aaa,aab");
        d.o("aaa", "aaa");
    }

    @Test
    public void testHasChildren() {
        class D {
            void T(String input) {
                assertNotNull(set.ceiling(input));
            }

            void F(String input) {
                assertNull(set.ceiling(input));
            }
        }
        D d = new D(); //
        d.T("");
        d.T("n");
        d.T("nam");
        d.T("name");
        d.T("a");
        d.T("aa");
        d.T("aaa");
        d.F("bb");
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
