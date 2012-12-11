package net.bodz.bas.c.object;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

public class UseNet<T>
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private final boolean checked;
    private final Map<T, Set<T>> nextMap;
    private final Map<T, Set<T>> prevMap;

    public UseNet() {
        this(true);
    }

    public UseNet(boolean checked) {
        this.checked = checked;
        nextMap = new IdentityHashMap<>();
        prevMap = new IdentityHashMap<>();
    }

    public boolean isChecked() {
        return checked;
    }

    @SafeVarargs
    public final void link(T node, T... nexts) {
        if (checked) {
            Set<T> closure = getPrevClosure(node);
            if (closure.contains(node))
                throw new IllegalStateException("bad graph.");
            for (T next : nexts)
                if (closure.contains(next))
                    throw new IllegalArgumentException("Loop isn't allowed: " + next);
        }

        Set<T> nextSet = resolveNextSet(node);
        for (T next : nexts) {
            nextSet.add(next);
            Set<T> prevSet = resolvePrevSet(next);
            prevSet.add(node);
        }
    }

    @SafeVarargs
    public final void invlink(T node, T... prevs) {
        if (checked) {
            Set<T> closure = getNextClosure(node);
            if (closure.contains(node))
                throw new IllegalStateException("bad graph.");
            for (T prev : prevs)
                if (closure.contains(prev))
                    throw new IllegalArgumentException("Loop isn't allowed: " + prev);
        }

        Set<T> prevSet = resolvePrevSet(node);
        for (T prev : prevs) {
            prevSet.add(prev);
            Set<T> nextSet = resolveNextSet(prev);
            nextSet.add(node);
        }
    }

    public Set<T> getKeySet() {
        return nextMap.keySet();
    }

    public Set<T> getInvKeySet() {
        return prevMap.keySet();
    }

    public Set<T> getNextClosure(T node) {
        Set<T> closure = new IdentityHashSet<>();
        fillNextClosure(closure, node);
        return closure;
    }

    public Set<T> getPrevClosure(T node) {
        Set<T> closure = new IdentityHashSet<>();
        fillPrevClosure(closure, node);
        return closure;
    }

    int fillNextClosure(Set<T> closure, T node) {
        int dup = 0;
        Set<T> nextSet = nextMap.get(node);
        if (nextSet != null)
            for (T next : nextSet) {
                if (!closure.add(next))
                    dup++;
                fillNextClosure(closure, next);
            }
        return dup;
    }

    int fillPrevClosure(Set<T> closure, T node) {
        int dup = 0;
        Set<T> prevSet = prevMap.get(node);
        if (prevSet != null)
            for (T prev : prevSet) {
                if (!closure.add(prev))
                    dup++;
                fillPrevClosure(closure, prev);
            }
        return dup;
    }

    public Set<T> getNexts(T node) {
        Set<T> nextSet = nextMap.get(node);
        if (nextSet == null)
            return null;
        else
            return Collections.unmodifiableSet(nextSet);
    }

    public Set<T> getPrevs(T node) {
        Set<T> prevSet = prevMap.get(node);
        if (prevSet == null)
            return null;
        else
            return Collections.unmodifiableSet(prevSet);
    }

    synchronized Set<T> resolveNextSet(T node) {
        Set<T> nextSet = nextMap.get(node);
        if (nextSet == null) {
            nextSet = new HashSet<>();
            nextMap.put(node, nextSet);
        }
        return nextSet;
    }

    synchronized Set<T> resolvePrevSet(T node) {
        Set<T> prevSet = prevMap.get(node);
        if (prevSet == null) {
            prevSet = new HashSet<>();
            prevMap.put(node, prevSet);
        }
        return prevSet;
    }

}
