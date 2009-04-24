package net.bodz.bas.util;

import java.util.Set;

import net.bodz.bas.codec.DefaultTextCodec;
import net.bodz.bas.codec.TextCodec;
import net.bodz.bas.lang.err.CheckException;
import net.bodz.bas.lang.err.EncodeException;
import net.bodz.bas.nls.AppNLS;
import net.bodz.bas.types.util.IdentSet;

public class Counter {

    private String          displayName;

    private TextCodec<Long> codec     = defaultCodec;
    private boolean         precoded;
    private long            value;
    private long            init;
    private long            increment = 1;

    private Set<Counter>    parents;
    private long            timestamp;
    private int             timeout;

    private int             epsilon   = 1;

    static TextCodec<Long>  defaultCodec;
    static {
        defaultCodec = new DefaultTextCodec<Long>(Long.class);
    }

    public Counter(String name) {
        this.displayName = name;
    }

    public void init() {
        set(init);
    }

    public void increase() {
        set(value + increment);
    }

    public void set(long value) {
        this.value = value;
        this.timestamp = System.currentTimeMillis();
    }

    public void refresh() {
        if (isExpired())
            set(init);
    }

    public long get() {
        return value;
    }

    public long get(boolean refresh) {
        if (refresh)
            refresh();
        return value;
    }

    public long getAndIncrease() {
        long n = get(true);
        increase();
        return n;
    }

    public String format(long value) {
        try {
            return codec.encode(value);
        } catch (EncodeException e) {
            throw new RuntimeException(e);
        }
    }

    public String format() {
        return format(value);
    }

    public String formatAndIncrease(boolean refresh) {
        if (refresh)
            refresh();
        long n = get();
        increase();
        return format(n);
    }

    public String getName() {
        return displayName;
    }

    public TextCodec<Long> getCodec() {
        return codec;
    }

    public void setCodec(TextCodec<Long> codec) {
        this.codec = codec;
    }

    public boolean isPrecoded() {
        return precoded;
    }

    public void setPrecoded(boolean precoded) {
        this.precoded = precoded;
    }

    public long getInit() {
        return init;
    }

    public void setInit(long init) {
        this.init = init;
    }

    public long getIncrement() {
        return increment;
    }

    public void setIncrement(long increment) {
        this.increment = increment;
    }

    public Set<Counter> getParents() {
        return parents;
    }

    public void setParents(Set<Counter> parents) {
        this.parents = parents;
    }

    public void check() throws CheckException {
        IdentSet uniq = new IdentSet();
        _check(uniq, this);
    }

    private void _check(IdentSet uniq, Counter c) throws CheckException {
        if (uniq.contains(c))
            throw new CheckException(AppNLS.getString("Counter.loopDetected") + c); //$NON-NLS-1$
        uniq.add(c);
        Iterable<Counter> parents = getParents();
        if (parents != null)
            for (Counter p : parents)
                _check(uniq, p);
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getEpsilon() {
        return epsilon;
    }

    public void setEpsilon(int epsilon) {
        this.epsilon = epsilon;
    }

    public boolean isExpired() {
        Iterable<Counter> parents = getParents();
        for (Counter parent : parents) {
            if (parent.isExpired())
                return true;
            if (timestamp + epsilon < parent.timestamp)
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return displayName + ' ' + format();
    }

}
