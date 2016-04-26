package net.bodz.bas.repr.path;

import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.c.object.IdentityHashSet;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.codegen.ExcludedFromIndex;
import net.bodz.bas.t.order.PriorityComparator;

@ExcludedFromIndex
public class CompositePathDispatcher
        extends AbstractPathDispatcher {

    static final Logger logger = LoggerFactory.getLogger(CompositePathDispatcher.class);

    private Set<IPathDispatcher> dispatchers;
    private int maxDispatchCount = 10000;
    private boolean checkDeadLoop = true;

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

    public int getMaxDispatchCount() {
        return maxDispatchCount;
    }

    public void setMaxDispatchCount(int maxDispatchCount) {
        this.maxDispatchCount = maxDispatchCount;
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

        Process process = new Process(previous, tokens);
        process.run();
        return process.arrival;
    }

    class Process {
        IPathArrival arrival;
        ITokenQueue tokens;
        int count;
        Set<Object> loopArounds;

        public Process(IPathArrival previous, ITokenQueue tokens) {
            this.arrival = previous;
            this.tokens = tokens;
            if (checkDeadLoop)
                loopArounds = new IdentityHashSet<>();
        }

        public void run()
                throws PathDispatchException {
            while (true) {
                // no more token: must be checked in every dispatcher implementation.
                // if (tokens.isEmpty()) break;

                if (!move()) // unknown token encounterred.
                {
                    // arrival = null;
                    break;
                }

                logger.debug("    " + arrival);
                Object target = arrival.getTarget();
                if (target == null)
                    break;

                if (checkDeadLoop) {
                    if (arrival.getConsumedTokens().length == 0) {
                        if (!loopArounds.add(arrival.getTarget()))
                            throw new PathDispatchException(String.format("Dead loop detected, n=%d.",
                                    loopArounds.size()));
                    } else
                        loopArounds.clear();
                }

                if (++count > maxDispatchCount)
                    throw new PathDispatchException(String.format("Too many dispatches (%d), maybe dead loop?",
                            maxDispatchCount));
            }
        }

        boolean move()
                throws PathDispatchException {
            IPathArrival tmp = arrival;
            tokens.save();
            for (IPathDispatcher dispatcher : dispatchers) {
                IPathArrival next = dispatcher.dispatch(tmp, tokens);
                if (next != null) { // null if token is not recognized by the dispatcher.
                    arrival = next;
                    return true;
                }
            }
            tokens.restore();
            return false;
        }

    }

}
