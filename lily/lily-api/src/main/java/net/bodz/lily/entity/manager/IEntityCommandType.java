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

    default String[] getCommandNames() {
        String name = getPreferredName();
        if (name == null)
            return new String[0];
        else
            return new String[] { name };
    }

    /**
     * content command can be dispatched by: <code>/FooBar/(id)/cmd</code>
     *
     * instead of <code>/FooBar/cmd</code>
     */
    boolean isContentCommand();

    IEntityCommandProcess createProcess(IEntityCommandContext context);

    boolean isEnabled(IEntityTypeInfo info);

    boolean checkPathValid(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException;

}
