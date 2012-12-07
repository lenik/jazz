package net.bodz.bas.repr.path;

import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.codegen.ExcludedFromIndex;
import net.bodz.bas.util.order.PriorityComparator;

@ExcludedFromIndex
public class CompositePathDispatcher
        extends AbstractPathDispatcher {

    static final Logger logger = LoggerFactory.getLogger(CompositePathDispatcher.class);

    private Set<IPathDispatcher> dispatchers;
    private int maxDispatchCount = 100;

    public CompositePathDispatcher() {
        dispatchers = new TreeSet<IPathDispatcher>(PriorityComparator.INSTANCE);
    }

    public Set<IPathDispatcher> getDispatchers() {
        return dispatchers;
    }

    protected void addDispatcher(IPathDispatcher dispatcher) {
        dispatchers.add(dispatcher);
    }

    protected void removeDispatcher(IPathDispatcher dispatcher) {
        dispatchers.remove(dispatcher);
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens)
            throws PathDispatchException {
        if (previous == null)
            throw new NullPointerException("previous");
        Object target = previous.getTarget();
        if (target == null)
            throw new PathDispatchException("null target.");

        logger.debug("Dispatch " + tokens);
        if (dispatchers.isEmpty())
            return null;

        IPathArrival arrival = previous;
        int count = 0;

        while (!tokens.isEmpty()) {
            boolean processed = false;

            for (IPathDispatcher dispatcher : dispatchers) {
                IPathArrival next = dispatcher.dispatch(arrival, tokens);
                if (next != null) {
                    arrival = next;
                    processed = true;
                    break;
                }
            }

            if (!processed)
                break;

            logger.debug("    " + arrival);

            target = arrival.getTarget();
            if (target == null)
                break;

            if (++count > maxDispatchCount)
                throw new PathDispatchException(String.format("Too many dispatches (%d), maybe dead loop?",
                        maxDispatchCount));
        }

        return arrival;
    }

}
