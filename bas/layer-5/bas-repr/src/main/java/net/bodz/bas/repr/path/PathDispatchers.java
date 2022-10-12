package net.bodz.bas.repr.path;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.c.object.IdentityHashSet;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.codegen.ExcludedFromIndex;
import net.bodz.bas.t.order.PriorityComparator;
import net.bodz.bas.t.variant.IVariantMap;

@ExcludedFromIndex
public class PathDispatchers
        implements
            IPathDispatcher {

    static final Logger logger = LoggerFactory.getLogger(PathDispatchers.class);

    private Set<IPathDispatcher> dispatchers;
    private int maxDispatchCount = 10000;
    private boolean checkDeadLoop = true;
    boolean multipleEnabled;
    boolean removeDups;

    public PathDispatchers() {
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
    public IPathArrival dispatch(IPathArrival previous, Object source, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        if (source == null)
            throw new PathDispatchException("null source.");

        logger.debug("Dispatch " + tokens);
        Process process = new Process(previous, tokens, q);
        List<Process> list = process.run();
        if (list.isEmpty())
            return null;
        Process first = list.get(0);

        // write the token queue state.
        tokens.skip((tokens.available() - first.tokens.available()));
        if (first.tokens.isStopped())
            tokens.stop();

        return first.start;
    }

    public List<IPathArrival> dispatchMultiple(Object startObject, ITokenQueue tokens, IVariantMap<String> q) {
        PathArrival start = new PathArrival(startObject, tokens.getRemainingPath());
        List<IPathArrival> arrivals = dispatchMultiple(start, tokens, q);
        for (IPathArrival arrival : arrivals) {
            if (!arrival.getRemainingPath().isEmpty())
                ; // throw new IncompleteDispatchException(tokens.toString());
        }
        return arrivals;
    }

    public List<IPathArrival> dispatchMultiple(MultipleTargets startObjects, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        IPathArrival start = new PathArrival(startObjects, tokens.getRemainingPath());
        List<IPathArrival> starts = MultipleTargets.extract(start);
        List<IPathArrival> results = dispatchMultiple(starts, tokens, q);
        return results;
    }

    public List<IPathArrival> dispatchMultiple(List<IPathArrival> previouses, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        if (previouses == null)
            throw new NullPointerException("previouses");
        List<IPathArrival> all = previouses;

        for (IPathArrival previous : previouses) {
            Object target = previous.getTarget();
            if (target == null)
                throw new PathDispatchException("null prev.target.");

            logger.debug("Dispatch " + tokens);

            Process process = new Process(previous, tokens, q);
            for (Process p : process.run())
                all.add(p.start);
        }
        return all;
    }

    class Process {
        IPathArrival start;
        ITokenQueue tokens;
        IVariantMap<String> q;
        Set<Object> marks;

        public Process(IPathArrival start, ITokenQueue tokens, IVariantMap<String> q) {
            this.start = start;
            this.tokens = tokens;
            this.q = q;
            if (checkDeadLoop)
                marks = new IdentityHashSet<Object>();
        }

        public List<Process> run()
                throws PathDispatchException {
            List<Process> ended = new ArrayList<Process>();

            int index = 0;
            List<Process> procs = Arrays.asList(this);
            while (true) {
                // no more token: must be checked in every dispatcher implementation.
                // if (tokens.isEmpty()) break;

                List<Process> children = new ArrayList<Process>();
                for (Process proc : procs) {
                    for (Process child : proc.stepForward()) {
                        IPathArrival arrival = child.start;
                        logger.debug("    " + arrival);
                        Object target = arrival.getTarget();
                        if (target == null)
                            ended.add(child);
                        else
                            children.add(child);
                    }
                }
                if (children.isEmpty()) // unknown token encounterred.
                    break;
                procs = children;

                if (++index > maxDispatchCount)
                    throw new PathDispatchException(
                            String.format("Too many dispatches (%d), maybe dead loop?", maxDispatchCount));
            }

            for (Process proc : procs)
                ended.add(proc);

            return ended;
        }

        List<Process> stepForward()
                throws PathDispatchException {
            List<Process> children = new ArrayList<Process>();
            Set<IPathArrival> arrivals = removeDups ? new IdentityHashSet<IPathArrival>() : null;

            ITokenQueue tmp = null;
            for (IPathDispatcher dispatcher : dispatchers) {
                if (tmp == null)
                    tmp = tokens.clone();

                IPathArrival arrival = dispatcher.dispatch(this.start, tmp, q);
                if (arrival == null) // null if token is not recognized by the dispatcher.
                    continue;
                if (arrival.isMultiple())
                    throw new NotImplementedException();

                if (multipleEnabled) {
                    if (!removeDups || arrivals.add(arrival)) {
                        Process child = new Process(arrival, tmp, q);
                        children.add(child);
                    }
                    tmp = null;
                } else {
                    this.start = arrival;
                    tokens = tmp;
                    children.add(this);
                    break;
                }
            }

            int n = children.size();
            for (int i = 0; i < n; i++) {
                Process child = children.get(i);
                if (i == 0)
                    child.marks = marks;
                else
                    child.marks = new IdentityHashSet<Object>(marks);
                child.checkDeadLoop(child.start);
            }
            return children;
        }

        void checkDeadLoop(IPathArrival arrival)
                throws PathDispatchException {
            if (!checkDeadLoop)
                return;
            Object target = arrival.getTarget();
            if (target == null)
                return;
            if (arrival.getConsumedTokens().length == 0) {
                if (!marks.add(target))
                    throw new PathDispatchException(String.format("Dead loop detected, n=%d.", marks.size()));
            } else
                marks.clear();
        }

        @Override
        public String toString() {
            return start.getTarget() + " | " + tokens.getRemainingPath();
        }

    } // Process

}
