package net.bodz.bas.text.lop;

public class _LexMatch implements Comparable<_LexMatch> {

    private int order;
    private int declOrder;
    private String state;
    private String value;
    private boolean mayReturn;

    public _LexMatch(LexMatch a, int declOrder) {
        value = a.value();
        order = a.order();
        state = a.state();
        mayReturn = a.mayReturn();
        this.declOrder = declOrder;
    }

    @Override
    public int compareTo(_LexMatch o) {
        int c = state.compareTo(o.state);
        if (c != 0)
            return c;
        c = order - o.order;
        if (c != 0)
            return c;
        c = declOrder - o.declOrder;
        return c;
    }

    public String getState() {
        return state;
    }

    public String getValue() {
        return value;
    }

    public boolean isMayReturn() {
        return mayReturn;
    }

}
