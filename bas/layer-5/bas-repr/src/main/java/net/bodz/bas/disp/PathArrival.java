package net.bodz.bas.disp;

import java.util.Arrays;
import java.util.Date;

import net.bodz.bas.c.object.ITreeDump;
import net.bodz.bas.c.object.TreeDump;
import net.bodz.bas.c.string.StringArray;
import net.bodz.bas.disp.util.ArrivalBacktraceCallback;
import net.bodz.bas.disp.util.ReversedPathTokens;
import net.bodz.bas.util.Nullables;

public class PathArrival
        implements IPathArrival {

    private final IPathArrival parent;
    private String[] consumedTokens = new String[0];
    private Object target;
    private String restPath;
    private Date expires;

    public PathArrival(Object startTarget) {
        this.parent = null;
        this.target = startTarget;
    }

    public PathArrival(IPathArrival parent, Object target, String... consumedTokens) {
        if (consumedTokens == null)
            throw new NullPointerException("consumedTokens");
        this.parent = parent;
        this.target = target;
        this.consumedTokens = consumedTokens;
    }

    @Override
    public IPathArrival getParent() {
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

    @Override
    public String getRestPath() {
        return restPath;
    }

    public void setRestPath(String parameterPath) {
        this.restPath = parameterPath;
    }

    /**
     * For example:
     * 
     * <pre>
     * foo/book/123 => foo/bookStore/book
     * -> book: ""
     * -> bookStore: "123"
     * -> foo: "book/123"
     * </pre>
     */
    @Override
    public <E extends Exception> boolean backtrace(ArrivalBacktraceCallback<E> callback)
            throws E {
        ReversedPathTokens consumedRpt = new ReversedPathTokens();

        IPathArrival node = this;
        while (node != null) {

            if (callback.arriveBack(node))
                return true;

            String[] tokens = node.getConsumedTokens();
            for (int i = tokens.length - 1; i >= 0; i--)
                consumedRpt.add(tokens[i]);

            node = node.getParent();
            if (node != null)
                node.setRestPath(consumedRpt.toString());
        }

        return false;
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
            sb.append(TreeDump.dump(dumpable));
        } else {
            sb.append(target);
        }

        return sb.toString();
    }

}
