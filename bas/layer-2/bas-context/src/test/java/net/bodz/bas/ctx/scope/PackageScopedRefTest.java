package net.bodz.bas.ctx.scope;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.ctx.scope.impl.PackageScopeInstance;

public class PackageScopedRefTest
        extends Assert {

    static class AddressVars
            extends ScopedRef<String> {

        public AddressVars() {
            super(String.class);
        }

        public void addMoreAddr(IScopeInstance scope, String moreAddr) {
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
    AddressVars addressVars = new AddressVars();

    class Inner {

        public String getAddress() {
            PackageScopeInstance callerId = PackageScopeInstance.forCaller();
            return addressVars.get(callerId);
        }

        public void addMoreAddr(String s) {
            PackageScopeInstance callerId = PackageScopeInstance.forCaller();
            addressVars.addMoreAddr(callerId, s);
        }

    }

    @Test
    public void testSetInnerKeepOuter()
            throws Exception {
        PackageScopeInstance scope = PackageScopeInstance.forCaller();
        String outerAddr = addressVars.get(scope);
        assertNull(outerAddr);

        Inner inner = new Inner();
        assertNull(inner.getAddress());

        inner.addMoreAddr("a");
        assertEquals("a", inner.getAddress());
        inner.addMoreAddr("b");
        assertEquals("ab", inner.getAddress());

        outerAddr = addressVars.get(scope);
        assertNull(outerAddr);
    }

    @Test
    public void testSetOuterChangeInner()
            throws Exception {
        PackageScopeInstance scope = PackageScopeInstance.forCaller();
        String outerAddr = addressVars.get(scope);
        assertNull(outerAddr);

        Inner inner = new Inner();
        assertNull(inner.getAddress());

        addressVars.set(scope, "x");
        assertEquals("x", addressVars.get(scope));
        // assertNull(inner.getAddress());
        assertEquals("x", inner.getAddress());
    }

}
