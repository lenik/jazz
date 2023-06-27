package net.bodz.lily.entity.manager;

import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.t.order.IPriority;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.type.IEntityTypeInfo;

public interface IEntityCommandType
        extends
            IPriority {

    String getPreferredName();

    /**
     * content command can be dispatched by: <code>/FooBar/(id)/cmd</code>
     *
     * instead of <code>/FooBar/cmd</code>
     */
    boolean isContentCommand();

    IEntityCommandProcess createProcess(IEntityCommandContext context);

    boolean isEnabled(IEntityTypeInfo info);

    boolean checkValid(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException;

}
