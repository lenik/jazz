package net.bodz.lily.entity.manager;

import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.t.order.IPriority;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.type.IEntityTypeInfo;

public interface IEntityCommandType
        extends
            IPriority {

    @NotNull
    String getUniqueId();

    default String[] getCommandNames() {
        String name = getUniqueId();
        return new String[] { name };
    }

    /**
     * The method name is case sensitive, usually it should be in upper case.
     */
    String[] getAcceptedMethods();

    default boolean isAcceptedMethod(String method) {
        for (String a : getAcceptedMethods())
            if (a.equals(method))
                return true;
        return false;
    }

    /**
     * content command can be dispatched by: <code>/FooBar/(id)/cmd</code>
     *
     * instead of <code>/FooBar/cmd</code>
     */
    boolean isContentCommand();

    IEntityCommandProcess createProcess(IEntityCommandContext context, ResolvedEntity resolvedEntity);

    boolean isEnabled(IEntityTypeInfo info);

    boolean checkPathValid(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException;

}
