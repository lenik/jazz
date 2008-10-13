package net.bodz.bas.types;

import net.bodz.bas.lang.err.ParseException;

public interface TypeParser {

    @Deprecated
    boolean variant();

    Object parse(String text) throws ParseException;

}
