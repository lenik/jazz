package net.bodz.bas.text.lop;

import java.io.IOException;
import java.util.NoSuchElementException;

import net.bodz.bas.util.exception.ParseException;

public interface Parser {

    Object parse(Lexer in) throws IOException, ParseException;

    Object getValue();

    void setValue(Object value);

    int token(String name) throws NoSuchElementException;

    int state();

    int state(int stackRelative);

}
