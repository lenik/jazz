package net.bodz.bas.context;

import org.junit.Assert;
import org.junit.Test;

public class PackageContextTest
        extends Assert {

    static class ContextAddress
            extends ContextLocal<String> {

        public void addMoreAddr(IContextId context, String moreAddr) {
            String s = get(context);
            if (s == null)
                s = moreAddr;
            else
                s += moreAddr;
            set(context, s);
        }

    }

    /**
     * for JUnit test, the static-modifier is off.
     */
    ContextAddress contextAddress = new ContextAddress();

    class Inner {

        public String getAddress() {
            PackageContextId innerContext = PackageContextId.getCallerPackageContext();
            return contextAddress.get(innerContext);
        }

        public void setMoreAddr(String s) {
            PackageContextId innerContext = PackageContextId.getCallerPackageContext();
            contextAddress.addMoreAddr(innerContext, s);
        }

    }

    @Test
    public void testSetInnerKeepOuter()
            throws Exception {
        PackageContextId outerContext = PackageContextId.getCallerPackageContext();
        String outerAddr = contextAddress.get(outerContext);
        assertNull(outerAddr);

        Inner inner = new Inner();
        assertNull(inner.getAddress());

        inner.setMoreAddr("a");
        assertEquals("a", inner.getAddress());
        inner.setMoreAddr("b");
        assertEquals("ab", inner.getAddress());

        outerAddr = contextAddress.get(outerContext);
        assertNull(outerAddr);
    }

    @Test
    public void testSetOuterChangeInner()
            throws Exception {
        PackageContextId outer = PackageContextId.getCallerPackageContext();
        String outerAddr = contextAddress.get(outer);
        assertNull(outerAddr);

        Inner inner = new Inner();
        assertNull(inner.getAddress());

        contextAddress.set(outer, "x");
        assertEquals("x", contextAddress.get(outer));
        // assertNull(inner.getAddress());
        assertEquals("x", inner.getAddress());
    }

}
