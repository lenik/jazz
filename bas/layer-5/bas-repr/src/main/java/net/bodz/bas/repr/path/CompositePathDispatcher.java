package net.bodz.bas.repr.path;

import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.fn.EvalException;
import net.bodz.bas.fn.IEvaluable;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.codegen.ExcludedFromIndex;
import net.bodz.bas.t.order.PriorityComparator;

@ExcludedFromIndex
public class CompositePathDispatcher
        extends AbstractPathDispatcher {

    static final Logger logger = LoggerFactory.getLogger(CompositePathDispatcher.class);

    private Set<IPathDispatcher> dispatchers;
    private int maxDispatchCount = 100;
    private int maxEvalDepth = 10;

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

    public int getMaxRefDepth() {
        return maxEvalDepth;
    }

    public void setMaxRefDepth(int maxRefDepth) {
        this.maxEvalDepth = maxRefDepth;
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

        public Process(IPathArrival previous, ITokenQueue tokens) {
            this.arrival = previous;
            this.tokens = tokens;
        }

        public void run()
                throws PathDispatchException {
            while (!tokens.isEmpty()) {
                if (!move())
                    break;

                logger.debug("    " + arrival);
                Object target = arrival.getTarget();
                if (target == null)
                    break;

                if (++count > maxDispatchCount)
                    throw new PathDispatchException(String.format("Too many dispatches (%d), maybe dead loop?",
                            maxDispatchCount));
            }
        }

        boolean move()
                throws PathDispatchException {
            IPathArrival tmp = arrival;
            tokens.save();
            for (int refDepth = 0; refDepth < maxEvalDepth; refDepth++) {
                for (IPathDispatcher dispatcher : dispatchers) {
                    IPathArrival next = dispatcher.dispatch(tmp, tokens);
                    if (next != null) {
                        arrival = next;
                        return true;
                    }
                }

                Object target = tmp.getTarget();
                if (target instanceof IEvaluable<?>) {
                    IEvaluable<?> ref = (IEvaluable<?>) target;
                    try {
                        target = ref.eval();
                    } catch (EvalException e) {
                        throw new PathDispatchException(e.getMessage(), e);
                    }
                    tmp = new PathArrival(tmp, target, new String[0], tmp.getRemainingPath());
                } else
                    break;
            }
            tokens.restore();
            return false;
        }

    }

}
