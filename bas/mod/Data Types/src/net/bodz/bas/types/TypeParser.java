package net.bodz.bas.types;

import net.bodz.bas.lang.err.ParseException;

public interface TypeParser<T> {

    T parse(String text) throws ParseException;

}
