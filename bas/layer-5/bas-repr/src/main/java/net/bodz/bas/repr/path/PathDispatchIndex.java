package net.bodz.bas.repr.path;

import java.util.ServiceLoader;

import net.bodz.bas.meta.codegen.IndexedTypeLoader;

/**
 * The dispatcher facade. This is also a {@link IPathDispatcher}, though it needn't be.
 * <p>
 * This facade is commonly used by HttpServlet or Filter, which accepts the initial URL prefix, and
 * pass down the rest of the tokens.
 */
@IndexedTypeLoader(IPathDispatcher.class)
public class PathDispatchIndex
        extends PathDispatchers {

    public PathDispatchIndex() {
        for (IPathDispatcher dispatcher : ServiceLoader.load(IPathDispatcher.class))
            addDispatcher(dispatcher);
    }

    private static final PathDispatchIndex instance = new PathDispatchIndex();

    public static PathDispatchIndex getInstance() {
        return instance;
    }

}
