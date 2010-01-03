package net.bodz.bas.commons.callbacks;

import net.bodz.bas.commons.exception.ExceptionCauses;
import net.bodz.bas.exceptions.RuntimizedException;

import org.junit.Test;

public class RunTargetExceptionTest {

    @ExceptionCauses ( Exception.class)
    void functionThrowsRuntimeExceptionOnly() {
        throw new RuntimizedException(new Exception("Inner"));
    }

    @Test ( expected = Exception.class)
    public void testDecompose()
            throws Exception {
        try {
            functionThrowsRuntimeExceptionOnly();
        } catch (RuntimeException e) {
            RuntimizedException.decompose(Exception.class, e);
        }
    }

    @Test ( expected = Exception.class)
    public void testDecomposeRunnable()
            throws Exception {
        RuntimizedException.decompose(Exception.class, new Runnable() {
            @Override
            public void run() {
                functionThrowsRuntimeExceptionOnly();
            }
        });
    }

}
