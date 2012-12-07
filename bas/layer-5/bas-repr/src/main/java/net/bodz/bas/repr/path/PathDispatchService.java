package net.bodz.bas.repr.path;

import java.util.ServiceLoader;

import net.bodz.bas.meta.codegen.ExcludedFromIndex;

/**
 * The dispatcher facade. This is also a {@link IPathDispatcher}, though it needn't be.
 * <p>
 * This facade is commonly used by HttpServlet or Filter, which accepts the initial URL prefix, and
 * pass down the rest of the tokens.
 */
@ExcludedFromIndex
public class PathDispatchService
        extends CompositePathDispatcher {

    public PathDispatchService() {
        for (IPathDispatcher dispatcher : ServiceLoader.load(IPathDispatcher.class))
            addDispatcher(dispatcher);
    }

    private static final PathDispatchService instance = new PathDispatchService();

    public static PathDispatchService getInstance() {
        return instance;
    }

}
