package net.bodz.bas.context;

import org.junit.Assert;
import org.junit.Test;

public class PackageContextTest
        extends Assert {

    static class AddressColo
            extends ContextLocal<String> {

        public void addMoreAddr(IContextId contextId, String moreAddr) {
            String val = get(contextId);
            if (val == null)
                val = moreAddr;
            else
                val += moreAddr;
            set(contextId, val);
        }

    }

    /**
     * for JUnit test, the static-modifier is off.
     */
    AddressColo addressColo = new AddressColo();

    class Inner {

        public String getAddress() {
            PackageContextId callerId = PackageContextId.forCaller();
            return addressColo.get(callerId);
        }

        public void addMoreAddr(String s) {
            PackageContextId callerId = PackageContextId.forCaller();
            addressColo.addMoreAddr(callerId, s);
        }

    }

    @Test
    public void testSetInnerKeepOuter()
            throws Exception {
        PackageContextId outerId = PackageContextId.forCaller();
        String outerAddr = addressColo.get(outerId);
        assertNull(outerAddr);

        Inner inner = new Inner();
        assertNull(inner.getAddress());

        inner.addMoreAddr("a");
        assertEquals("a", inner.getAddress());
        inner.addMoreAddr("b");
        assertEquals("ab", inner.getAddress());

        outerAddr = addressColo.get(outerId);
        assertNull(outerAddr);
    }

    @Test
    public void testSetOuterChangeInner()
            throws Exception {
        PackageContextId outerId = PackageContextId.forCaller();
        String outerAddr = addressColo.get(outerId);
        assertNull(outerAddr);

        Inner inner = new Inner();
        assertNull(inner.getAddress());

        addressColo.set(outerId, "x");
        assertEquals("x", addressColo.get(outerId));
        // assertNull(inner.getAddress());
        assertEquals("x", inner.getAddress());
    }

}
