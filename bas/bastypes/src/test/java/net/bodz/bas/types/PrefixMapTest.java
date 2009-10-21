package net.bodz.bas.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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
        class D {
            void o(String input, String expected) {
                Iterable<String> vals = map.ceilings(input);
                String actual = Strings.join(",", vals); //$NON-NLS-1$
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("n", "tom"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("na", "tom"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("nam", "tom"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("name", "tom"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("a", "aa,aaa,aab"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("aa", "aa,aaa,aab"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("aaa", "aaa"); //$NON-NLS-1$ //$NON-NLS-2$
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
        d.T(""); //$NON-NLS-1$
        d.T("n"); //$NON-NLS-1$
        d.T("nam"); //$NON-NLS-1$
        d.T("name"); //$NON-NLS-1$
        d.T("a"); //$NON-NLS-1$
        d.T("aa"); //$NON-NLS-1$
        d.T("aaa"); //$NON-NLS-1$
        d.F("bb"); //$NON-NLS-1$
    }

    @Test
    public void test_getParent() {
        final PrefixMap<String> map = new PrefixMap<String>();
        map.put("z.bas", "%bas"); //$NON-NLS-1$ //$NON-NLS-2$
        map.put("z.xml", "%xml"); //$NON-NLS-1$ //$NON-NLS-2$
        class D {
            void o(String abbr, String expected) {
                String parent = map.floorKey(abbr);
                String actual;
                if (parent == null)
                    actual = "==" + abbr; //$NON-NLS-1$
                else {
                    String expand = map.get(parent);
                    abbr = abbr.substring(parent.length());
                    actual = expand + abbr;
                }
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("hello", "==hello"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("z.bas", "%bas"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("z.bas123", "%bas123"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("z.bas/hello", "%bas/hello"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("z.ba", "==z.ba"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("z.xml", "%xml"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("z.", "==z."); //$NON-NLS-1$ //$NON-NLS-2$
    }

}
