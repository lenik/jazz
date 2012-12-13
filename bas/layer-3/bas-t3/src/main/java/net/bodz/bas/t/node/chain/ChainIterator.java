package net.bodz.bas.t.node.chain;

import java.util.Iterator;

import net.bodz.bas.c.java.util.PrefetchedIterator;
import net.bodz.bas.err.UnexpectedException;

public class ChainIterator<NT extends IChainNode>
        //
        extends PrefetchedIterator<NT>
        implements Iterator<NT> {

    private NT start;
    private NT current;
    private int state;

    public ChainIterator(NT start, int mode) {
        this.start = start;
        if (start == null) {
            state = _END;
            return;
        }
        switch (mode) {
        case All:
            state = START_PREV;
            break;
        case Rewind:
            current = start;
            while (true) {
                NT prev = (NT) current.getPrev();
                if (prev == null)
                    break;
                current = prev;
            }
            state = START_NEXT;
            break;
        case PrevIncl:
            state = START_PREV_ONLY;
            break;
        case NextIncl:
            state = START_NEXT;
            break;
        case PrevOnly:
            state = PREV_ONLY;
            break;
        case NextOnly:
            state = NEXT;
            break;
        case ResetHead:
        case ResetTail:
            throw new UnsupportedOperationException("not impl. yet");
        default:
            throw new IllegalArgumentException("invalid mode: " + mode);
        }
    }

    @Override
    protected NT fetch() {
        switch (state) {
        case START_PREV:
            state = PREV;
            return current = start;
        case START_PREV_ONLY:
            state = PREV_ONLY;
            return current = start;
        case START_NEXT:
            state = NEXT;
            return current = start;
        case PREV:
        case PREV_ONLY:
            current = (NT) current.getPrev();
            if (current == null) {
                current = start;
                state = state == PREV_ONLY ? _END : NEXT;
                return fetch();
            }
            return current;
        case NEXT:
            current = (NT) current.getNext();
            if (current == null) {
                state = _END;
                return end();
            }
            return current;
        case _END:
            return end();
        }
        throw new UnexpectedException();
    }

    public static final int All = 0;
    public static final int Rewind = 1;
    public static final int PrevIncl = 2;
    public static final int NextIncl = 3;
    public static final int PrevOnly = 4;
    public static final int NextOnly = 5;
    public static final int ResetHead = 6;
    public static final int ResetTail = 7;

    private static final int _END = 0;
    private static final int START_PREV = 1;
    private static final int START_PREV_ONLY = 2;
    private static final int START_NEXT = 3;
    private static final int NEXT = 4;
    private static final int PREV = 5;
    private static final int PREV_ONLY = 6;

}
