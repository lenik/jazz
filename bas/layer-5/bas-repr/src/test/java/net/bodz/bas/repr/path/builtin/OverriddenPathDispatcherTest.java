package net.bodz.bas.repr.path.builtin;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.IPathDispatcher;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.t.variant.IVariantMap;

public class OverriddenPathDispatcherTest
        extends Assert
        implements IPathDispatchable {

    public String field1 = "hello";

    IPathDispatcher fieldDispatcher = new FieldPathDispatcher();

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        return fieldDispatcher.dispatch(previous, tokens, q);
    }

    @Test
    public void testIndirectFieldDispatch()
            throws PathDispatchException {
        OverriddenPathDispatcher od = new OverriddenPathDispatcher();

        IPathArrival arrival = od.dispatch(this, "field1", null);
        Object result = arrival.getTarget();

        Assert.assertSame(field1, result);
    }

}
