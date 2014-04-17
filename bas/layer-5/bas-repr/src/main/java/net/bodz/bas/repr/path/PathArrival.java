package net.bodz.bas.repr.path;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
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
    private Date expires;

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

    @Override
    public IPathArrival getPrevious() {
        return parent;
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

    @Override
    public Date getExpires() {
        return expires;
    }

    public void expires(Date afterDate) {
        if (this.expires == null)
            this.expires = afterDate;
        else {
            // Get the min date
            int cmp = this.expires.compareTo(afterDate);
            if (cmp > 0)
                this.expires = afterDate;
        }
    }

    @Override
    public Date getMinExpires() {
        Date min = this.expires;
        IPathArrival prev = this;
        while ((prev = prev.getPrevious()) != null) {
            Date expires1 = prev.getExpires();
            if (min == null)
                min = expires1;
            else if (expires1 != null && expires1.compareTo(min) < 0)
                min = expires1;
        }
        return min;
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
        result = prime * result + ((expires == null) ? 0 : expires.hashCode());
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
        if (!Nullables.equals(expires, other.expires))
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
