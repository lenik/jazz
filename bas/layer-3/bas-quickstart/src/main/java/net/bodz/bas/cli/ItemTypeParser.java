package net.bodz.bas.cli;

import net.bodz.bas.commons.exceptions.ParseException;

/** reserved for future use */
public interface ItemTypeParser {

    Object parse(Object index, String text) throws ParseException;

}
