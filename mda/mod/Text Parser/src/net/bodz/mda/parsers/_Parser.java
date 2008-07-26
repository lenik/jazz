package net.bodz.mda.parsers;

public abstract class _Parser implements Parser {

    @Override
    public int state() {
        return state(0);
    }

}
