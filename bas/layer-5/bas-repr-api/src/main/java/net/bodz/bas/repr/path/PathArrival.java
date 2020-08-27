package net.bodz.bas.repr.path;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.bodz.bas.c.java.util.Arrays;
import net.bodz.bas.c.object.ITreeDump;
import net.bodz.bas.c.object.TreeDumps;
import net.bodz.bas.c.string.StringArray;

public class PathArrival
        implements IPathArrival {

    private IPathArrival parent;
    private String[] consumedTokens = new String[0];
    private Object resolver;
    private Object target;
    private boolean multiple;
    private String remainingPath;

    public PathArrival(Object startTarget, String remainingPath) {
        this.parent = null;
        this.target = startTarget;
        this.remainingPath = remainingPath;
    }

    public PathArrival(IPathArrival parent, Object resolver, Object target, String[] consumedTokens,
            String remainingPath) {
        if (consumedTokens == null)
            throw new NullPointerException("consumedTokens");
        this.parent = parent;
        this.resolver = resolver;
        this.target = target;
        this.consumedTokens = consumedTokens;
        this.remainingPath = remainingPath;
    }

    public PathArrival(IPathArrival parent, Object resolver, Object target, String consumedToken, String remainingPath) {
        this(parent, resolver, target, new String[] { consumedToken }, remainingPath);
    }

    public PathArrival(IPathArrival o) {
        this.parent = o.getPrevious();
        this.resolver = o.getResolver();
        this.target = o.getTarget();
        this.multiple = o.isMultiple();
        this.consumedTokens = o.getConsumedTokens();
        this.remainingPath = o.getRemainingPath();
    }

    public static PathArrival shift(IPathArrival parent, Object resolver, Object target, ITokenQueue tokens) {
        return shift(1, parent, resolver, target, tokens);
    }

    public static PathArrival shift(int n, IPathArrival parent, Object resolver, Object target, ITokenQueue tokens) {
        String[] array = tokens.shift(n);
        if (parent != null && parent.getTarget() == target) {
            String[] cat = Arrays.append(parent.getConsumedTokens(), array);
            return new PathArrival(parent.getPrevious(), resolver, target, cat, tokens.getRemainingPath());
        }
        return new PathArrival(parent, resolver, target, array, tokens.getRemainingPath());
    }

    @Override
    public IPathArrival getPrevious() {
        return parent;
    }

    @Override
    public IPathArrival getPrevious(int n) {
        IPathArrival a = this;
        for (int i = 0; i < n; i++)
            if ((a = a.getPrevious()) == null)
                break;
        return a;
    }

    @Override
    public IPathArrival getPrevious(Object target) {
        IPathArrival a = this;
        while (a != null) {
            if (a.getTarget() == target)
                return a;
            a = a.getPrevious();
        }
        return null;
    }

    @Override
    public String[] getConsumedTokens() {
        return consumedTokens;
    }

    public void setConsumedTokens(String[] consumedTokens) {
        this.consumedTokens = consumedTokens;
    }

    @Override
    public String getConsumedPath() {
        return StringArray.join("/", consumedTokens);
    }

    @Override
    public String getConsumedFullPath() {
        List<String> tokens = new ArrayList<String>();
        IPathArrival a = this;
        while (a != null) {
            String[] cv = a.getConsumedTokens();
            for (int i = cv.length - 1; i >= 0; i--)
                tokens.add(cv[i]);
            a = a.getPrevious();
        }
        Collections.reverse(tokens);
        return StringArray.join("/", tokens);
    }

    /**
     * Lazy-initialized: unset until its child node call {@link #fallback()}.
     */
    @Override
    public String getRemainingPath() {
        return remainingPath;
    }

    public Object getResolver() {
        return resolver;
    }

    public void setResolver(Object resolver) {
        this.resolver = resolver;
    }

    @Override
    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    @Override
    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    @Override
    public Object getLastNonNullTarget() {
        if (target != null)
            return target;

        if (parent == null)
            return null;

        return parent.getLastNonNullTarget();
    }

    @Override
    public PathArrivalList toList() {
        PathArrivalList list = new PathArrivalList();
        IPathArrival a = this;
        while (a != null) {
            list.add(a);
            a = a.getPrevious();
        }
        Collections.reverse(list);
        return list;
    }

    static PathArrivalList merge(PathArrivalList list) {
        PathArrivalList result = new PathArrivalList(list.size());
        PathArrival prev = null;
        for (IPathArrival a : list) {
            PathArrival mutable = new PathArrival(a);
            if (mutable.consumedTokens.length == 0) {
                // Transient: prev -> ... -> mutable.parent -> mutable.
                if (prev != null)
                    prev.target = mutable.target;
                else {
                    if (mutable.parent == null)
                        ; // "Transient path arrival from null."
                }
            } else {
                // Same target: prev -> ... -> mutable.target.
                if (prev != null && prev.target == mutable.target)
                    prev.target = mutable.target;
                else
                    result.add(prev = mutable);
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(consumedTokens);
        if (parent != null)
            result = prime * result + System.identityHashCode(parent);
        if (target != null)
            result = prime * result + System.identityHashCode(target);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof PathArrival))
            return false;
        PathArrival other = (PathArrival) obj;
        if (!Arrays.equals(consumedTokens, other.consumedTokens))
            return false;
        if (parent != other.parent)
            return false;
        if (target != other.target)
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(getConsumedPath());
        sb.append(" -> ");

        if (target instanceof ITreeDump) {
            ITreeDump dumpable = (ITreeDump) target;
            sb.append(TreeDumps.dump(dumpable));
        } else {
            sb.append(target);
        }

        return sb.toString();
    }

}
