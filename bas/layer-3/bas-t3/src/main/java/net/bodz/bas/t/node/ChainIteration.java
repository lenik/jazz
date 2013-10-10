package net.bodz.bas.t.node;

import java.util.Iterator;

public class ChainIteration {

    public static <NT extends IChainNode> Iterable<NT> siblings(final NT start, final int mode) {
        return new Iterable<NT>() {
            @Override
            public Iterator<NT> iterator() {
                return new ChainIterator<NT>(start, mode);
            }
        };
    }

    public static <NT extends IChainNode> Iterable<NT> siblings(final NT start, final boolean rewind) {
        return siblings(start, rewind ? ChainIterator.Rewind : ChainIterator.All);
    }

    public static <NT extends IChainNode> Iterable<NT> siblings(final NT start) {
        return siblings(start, false);
    }

    public static <NT extends IChainNode> Iterable<NT> nexts(final NT start, final boolean inclusive) {
        return siblings(start, inclusive ? ChainIterator.NextIncl : ChainIterator.NextOnly);
    }

    public static <NT extends IChainNode> Iterable<NT> nexts(final NT start) {
        return nexts(start, true);
    }

    public static <NT extends IChainNode> Iterable<NT> prevs(final NT start, final boolean inclusive) {
        return siblings(start, inclusive ? ChainIterator.PrevIncl : ChainIterator.PrevOnly);
    }

    public static <NT extends IChainNode> Iterable<NT> prevs(final NT start) {
        return prevs(start, false);
    }

}
