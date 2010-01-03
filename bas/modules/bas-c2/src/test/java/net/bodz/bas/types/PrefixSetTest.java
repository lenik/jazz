package net.bodz.bas.types;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import net.bodz.bas.lang.modules.collection.hierarchical.PrefixSet;
import net.bodz.bas.lang.text.util.Strings;

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
        class D {
            void o(String input, String expected) {
                Iterable<String> ceilings = set.ceilings(input);
                String actual = Strings.join(",", ceilings); //$NON-NLS-1$
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("n", "name"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("na", "name"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("nam", "name"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("name", "name"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("a", "aa,aaa,aab"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("aa", "aa,aaa,aab"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("aaa", "aaa"); //$NON-NLS-1$ //$NON-NLS-2$
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
    public void testCrossParent() {
        PrefixSet set = new PrefixSet();
        set.add("cat"); //$NON-NLS-1$
        set.add("catx"); //$NON-NLS-1$
        set.add("catz"); //$NON-NLS-1$
        assertEquals("catx", set.floor("catxy")); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("cat", set.floor("caty")); //$NON-NLS-1$ //$NON-NLS-2$
    }

}
