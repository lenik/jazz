package net.bodz.bas.ctx.scope;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.ctx.scope.ScopedRef;
import net.bodz.bas.ctx.scope.id.IScopeDescriptor;
import net.bodz.bas.ctx.scope.id.PackageScopeDescriptor;

public class PackageScopedRefTest
        extends Assert {

    static class AddressScr
            extends ScopedRef<String> {

        public AddressScr() {
            super(String.class);
        }

        public void addMoreAddr(IScopeDescriptor scope, String moreAddr) {
            String val = get(scope);
            if (val == null)
                val = moreAddr;
            else
                val += moreAddr;
            set(scope, val);
        }

    }

    /**
     * for JUnit test, the static-modifier is off.
     */
    AddressScr addressScr = new AddressScr();

    class Inner {

        public String getAddress() {
            PackageScopeDescriptor callerId = PackageScopeDescriptor.forCaller();
            return addressScr.get(callerId);
        }

        public void addMoreAddr(String s) {
            PackageScopeDescriptor callerId = PackageScopeDescriptor.forCaller();
            addressScr.addMoreAddr(callerId, s);
        }

    }

    @Test
    public void testSetInnerKeepOuter()
            throws Exception {
        PackageScopeDescriptor scope = PackageScopeDescriptor.forCaller();
        String outerAddr = addressScr.get(scope);
        assertNull(outerAddr);

        Inner inner = new Inner();
        assertNull(inner.getAddress());

        inner.addMoreAddr("a");
        assertEquals("a", inner.getAddress());
        inner.addMoreAddr("b");
        assertEquals("ab", inner.getAddress());

        outerAddr = addressScr.get(scope);
        assertNull(outerAddr);
    }

    @Test
    public void testSetOuterChangeInner()
            throws Exception {
        PackageScopeDescriptor scope = PackageScopeDescriptor.forCaller();
        String outerAddr = addressScr.get(scope);
        assertNull(outerAddr);

        Inner inner = new Inner();
        assertNull(inner.getAddress());

        addressScr.set(scope, "x");
        assertEquals("x", addressScr.get(scope));
        // assertNull(inner.getAddress());
        assertEquals("x", inner.getAddress());
    }

}
