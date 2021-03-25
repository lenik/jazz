package net.bodz.bas.test.junit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

public class JUnitApp<self_t>
        extends Assert {

    public JUnitApp() {
    }

    /**
     * Create a new instance like this using JUnit wrapper,
     *
     * @return Wrapped instance.
     */
    public self_t assemble() {
        return assemble(false);
    }

    /**
     * Wrap and wire this object, result in a new one if <code>dropThis</code> is specified, or hack
     * myself if <code>dropThis</code> is disabled. Spring CDI.
     *
     * @param dropThis
     *            Whether should hack into myself.
     *            <p>
     *            Currently, keep-this is not supported. A new instance should always be created.
     * @return Wrapped & Wired instance.
     */
    public self_t assemble(boolean dropThis) {
        if (dropThis) {
            @SuppressWarnings("unchecked")
            self_t wrapped = (self_t) JUnitHelper.createWrappedInstance(getClass());
            return wrapped;
        }

        @SuppressWarnings("unchecked")
        self_t _this = (self_t) this;

        try {
            JUnitHelper.beforeClass(getClass());
            JUnitHelper.beforeMethod(getClass(), this);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        return _this;
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    public void run() {
    }

    public void dump() {
    }

}
