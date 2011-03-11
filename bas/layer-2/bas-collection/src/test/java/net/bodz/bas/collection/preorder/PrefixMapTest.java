package net.bodz.bas.collection.preorder;

import org.junit.Assert;
import org.junit.Test;

public class PrefixMapTest
        extends Assert {

    PrefixMap<String> map = new PrefixMap<String>();

    public PrefixMapTest() {
        map.put("name", "tom");
        map.put("aa", "aa");
        map.put("aaa", "aaa");
        map.put("aab", "aab");
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
    public void test_getChildrenValues() {
        class D {
            void o(String input, String expected) {
                Iterable<String> vals = map.ceilings(input);
                String actual = join(",", vals);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("n", "tom");
        d.o("na", "tom");
        d.o("nam", "tom");
        d.o("name", "tom");
        d.o("a", "aa,aaa,aab");
        d.o("aa", "aa,aaa,aab");
        d.o("aaa", "aaa");
    }

    @Test
    public void test_hasChildren() {
        class D {
            void T(String input) {
                assertNotNull(map.ceilingKey(input));
            }

            void F(String input) {
                assertNull(map.ceilingKey(input));
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
    public void test_getParent() {
        final PrefixMap<String> map = new PrefixMap<String>();
        map.put("z.bas", "%bas");
        map.put("z.xml", "%xml");
        class D {
            void o(String abbr, String expected) {
                String parent = map.floorKey(abbr);
                String actual;
                if (parent == null)
                    actual = "==" + abbr;
                else {
                    String expand = map.get(parent);
                    abbr = abbr.substring(parent.length());
                    actual = expand + abbr;
                }
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("hello", "==hello");
        d.o("z.bas", "%bas");
        d.o("z.bas123", "%bas123");
        d.o("z.bas/hello", "%bas/hello");
        d.o("z.ba", "==z.ba");
        d.o("z.xml", "%xml");
        d.o("z.", "==z.");
    }

}
