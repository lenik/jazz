package net.bodz.bas.ctx.scope;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.ctx.scope.IScopeToken;
import net.bodz.bas.ctx.scope.PackageScopeToken;
import net.bodz.bas.ctx.scope.ScopedRef;

public class PackageScopeTokenTest
        extends Assert {

    static class AddressScr
            extends ScopedRef<String> {

        public AddressScr() {
            super(String.class);
        }

        public void addMoreAddr(IScopeToken scopeId, String moreAddr) {
            String val = get(scopeId);
            if (val == null)
                val = moreAddr;
            else
                val += moreAddr;
            set(scopeId, val);
        }

    }

    /**
     * for JUnit test, the static-modifier is off.
     */
    AddressScr addressColo = new AddressScr();

    class Inner {

        public String getAddress() {
            PackageScopeToken callerId = PackageScopeToken.forCaller();
            return addressColo.get(callerId);
        }

        public void addMoreAddr(String s) {
            PackageScopeToken callerId = PackageScopeToken.forCaller();
            addressColo.addMoreAddr(callerId, s);
        }

    }

    @Test
    public void testSetInnerKeepOuter()
            throws Exception {
        PackageScopeToken outerId = PackageScopeToken.forCaller();
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
        PackageScopeToken outerId = PackageScopeToken.forCaller();
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
