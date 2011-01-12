package net.bodz.bas.cli;

import net.bodz.bas.util.exception.ParseException;

/** reserved for future use */
public interface ItemTypeParser {

    Object parse(Object index, String text)
            throws ParseException;

}
