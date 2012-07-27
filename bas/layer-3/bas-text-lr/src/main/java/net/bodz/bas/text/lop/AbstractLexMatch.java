package net.bodz.bas.text.lop;

public class AbstractLexMatch
        implements Comparable<AbstractLexMatch> {

    private int order;
    private int declOrder;
    private String state;
    private String value;
    private boolean mayReturn;

    public AbstractLexMatch(LexMatch a, int declOrder) {
        value = a.value();
        order = a.order();
        state = a.state();
        mayReturn = a.mayReturn();
        this.declOrder = declOrder;
    }

    @Override
    public int compareTo(AbstractLexMatch o) {
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
