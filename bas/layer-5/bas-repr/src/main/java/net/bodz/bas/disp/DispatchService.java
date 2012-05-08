package net.bodz.bas.disp;

import java.util.ServiceLoader;
import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.log.api.Logger;
import net.bodz.bas.log.api.LoggerFactory;

/**
 * The dispatcher facade. This is also a {@link IDispatcher}, though it needn't be.
 * <p>
 * This facade is commonly used by HttpServlet or Filter, which accepts the initial URL prefix, and
 * pass down the rest of the tokens.
 */
public class DispatchService
        extends AbstractDispatcher {

    static Logger logger = LoggerFactory.getLogger(DispatchService.class);

    TreeSet<IDispatcher> dispatchers;

    // private DispatchTree tree;

    public DispatchService() {
        reloadProviders();
    }

    synchronized void reloadProviders() {
        dispatchers = new TreeSet<IDispatcher>(DispatcherComparator.getInstance());

        ServiceLoader<IDispatcher> dispatcherLoader;
        dispatcherLoader = ServiceLoader.load(IDispatcher.class);

        for (IDispatcher dispatcher : dispatcherLoader) {
            dispatchers.add(dispatcher);
        }
    }

    public Set<IDispatcher> getDispatchers() {
        return dispatchers;
    }

    private static int maxDispatches = 100;

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens)
            throws DispatchException {
        if (previous == null)
            throw new NullPointerException("previous");
        IPathArrival node = previous;

        if (dispatchers.isEmpty())
            logger.warn("No dispatch configured");

        int count = 0;

        logger.debug("Dispatch " + tokens);
        while (!tokens.isEmpty()) {
            boolean processed = false;

            for (IDispatcher dispatcher : dispatchers) {
                IPathArrival next = dispatcher.dispatch(node, tokens);
                if (next != null) {
                    node = next;
                    processed = true;
                    break;
                }
            }

            if (!processed)
                break;

            logger.debug("    " + node);

            if (++count > maxDispatches)
                throw new DispatchException(String.format("Dispatch-deadloop (%d) detected.", maxDispatches));
        }

        return node;
    }

    private static final DispatchService instance = new DispatchService();

    public static DispatchService getInstance() {
        return instance;
    }

}
