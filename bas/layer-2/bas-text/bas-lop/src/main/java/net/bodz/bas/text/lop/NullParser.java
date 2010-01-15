package net.bodz.bas.text.lop;

import java.io.IOException;
import java.util.NoSuchElementException;

import net.bodz.bas.exceptions.ParseException;

public class NullParser extends _Parser {

    private Object value;

    public NullParser() {
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public int state(int stackRelative) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int token(String name) throws NoSuchElementException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object parse(Lexer in) throws IOException, ParseException {
        return null;
    }

}
