package net.bodz.mda.parsers;

import java.io.IOException;
import java.io.Reader;
import java.util.NoSuchElementException;

import net.bodz.bas.lang.err.ParseException;

public interface Parser {

    Object parse(Reader in) throws IOException, ParseException;

    Object value();

    void setValue(Object value);

    int token(String name) throws NoSuchElementException;

    int state();

    int state(int stackRelative);

}
