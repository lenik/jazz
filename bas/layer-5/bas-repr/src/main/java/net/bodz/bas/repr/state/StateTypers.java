package net.bodz.bas.repr.state;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.AbstractCommonTypers;
import net.bodz.bas.typer.std.IFormatter;
import net.bodz.bas.typer.std.IParser;

public class StateTypers
        extends AbstractCommonTypers<State> {

    public StateTypers() {
        super(State.class);
    }

    @Override
    protected Object queryInt(int typerIndex) {
        switch (typerIndex) {
        case IParser.typerIndex:
        case IFormatter.typerIndex:
            return this;
        }
        return null;
    }

    @Override
    public State parse(String text)
            throws ParseException {
        State state = StateGroups.getState(text);
        if (state == null)
            throw new ParseException("Undefined state: " + text);
        return state;
    }

    @Override
    public final State parse(String text, IOptions options)
            throws ParseException {
        return parse(text);
    }

    @Override
    public String format(State state) {
        return state.getName();
    }

    @Override
    public final String format(State state, IOptions options) {
        return format(state);
    }

}
