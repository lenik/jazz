package net.bodz.bas.cli;

import net.bodz.bas.lang.err.ParseException;

/** reserved for future use */
public interface ItemTypeParser<T> {

    T parse(Object index, String text) throws ParseException;

}
