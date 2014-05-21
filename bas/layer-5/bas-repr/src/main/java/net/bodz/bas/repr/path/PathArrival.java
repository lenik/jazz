package net.bodz.bas.repr.path;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.bodz.bas.c.object.ITreeDump;
import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.c.object.TreeDumps;
import net.bodz.bas.c.string.StringArray;

public class PathArrival
        implements IPathArrival {

    private final IPathArrival parent;
    private String[] consumedTokens = new String[0];
    private Object target;
    private String remainingPath;

    public PathArrival(Object startTarget, String remainingPath) {
        this.parent = null;
        this.target = startTarget;
        this.remainingPath = remainingPath;
    }

    public PathArrival(IPathArrival parent, Object target, String[] consumedTokens, String remainingPath) {
        if (consumedTokens == null)
            throw new NullPointerException("consumedTokens");
        this.parent = parent;
        this.target = target;
        this.consumedTokens = consumedTokens;
        this.remainingPath = remainingPath;
    }

    public PathArrival(IPathArrival parent, Object target, String consumedToken, String remainingPath) {
        this(parent, target, new String[] { consumedToken }, remainingPath);
    }

    public static PathArrival shift(IPathArrival parent, Object target, ITokenQueue tokens) {
        String token = tokens.shift();
        return new PathArrival(parent, target, token, tokens.getRemainingPath());
    }

    public static PathArrival shift(int n, IPathArrival parent, Object target, ITokenQueue tokens) {
        String[] array = tokens.shift(n);
        return new PathArrival(parent, target, array, tokens.getRemainingPath());
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

    /**
     * Lazy-initialized: unset until its child node call {@link #fallback()}.
     */
    @Override
    public String getRemainingPath() {
        return remainingPath;
    }

    @Override
    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    @Override
    public Object getLastNonNullTarget() {
        if (target != null)
            return target;

        if (parent == null)
            return null;

        return parent.getLastNonNullTarget();
    }

    public List<IPathArrival> reverse() {
        List<IPathArrival> list = new ArrayList<IPathArrival>();
        IPathArrival a = this;
        while (a != null) {
            list.add(a);
            a = a.getPrevious();
        }
        Collections.reverse(list);
        return list;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(consumedTokens);
        result = prime * result + ((parent == null) ? 0 : parent.hashCode());
        result = prime * result + ((target == null) ? 0 : target.hashCode());
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
        if (!Nullables.equals(parent, other.parent))
            return false;
        if (!Nullables.equals(target, other.target))
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
