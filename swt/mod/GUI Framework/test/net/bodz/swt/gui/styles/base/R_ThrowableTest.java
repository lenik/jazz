package net.bodz.swt.gui.styles.base;

import net.bodz.bas.lang.err.IllegalUsageError;
import net.bodz.swt.gui.styles.TestMain;

public class R_ThrowableTest {

    public static void main(String[] args) {
        Throwable th = null;
        try {
            try {
                R_ThrowableTest.class.getField("non-exist"); //$NON-NLS-1$
            } catch (Throwable e) {
                throw new RuntimeException("getField failed", e); //$NON-NLS-1$
            }
        } catch (Exception e) {
            th = new IllegalUsageError("some test message", e); //$NON-NLS-1$
        }
        assert th != null;
        TestMain.test(th);
    }

}
