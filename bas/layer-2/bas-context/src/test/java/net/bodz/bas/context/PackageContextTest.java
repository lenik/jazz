package net.bodz.bas.context;

import org.junit.Assert;
import org.junit.Test;

public class PackageContextTest
        extends Assert {

    static class AddressCtl
            extends ContextLocal<String> {

        public AddressCtl() {
            super(String.class);
        }

        public void addMoreAddr(IContext contextId, String moreAddr) {
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
    AddressCtl addressColo = new AddressCtl();

    class Inner {

        public String getAddress() {
            PackageContext callerId = PackageContext.forCaller();
            return addressColo.get(callerId);
        }

        public void addMoreAddr(String s) {
            PackageContext callerId = PackageContext.forCaller();
            addressColo.addMoreAddr(callerId, s);
        }

    }

    @Test
    public void testSetInnerKeepOuter()
            throws Exception {
        PackageContext outerId = PackageContext.forCaller();
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
        PackageContext outerId = PackageContext.forCaller();
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
