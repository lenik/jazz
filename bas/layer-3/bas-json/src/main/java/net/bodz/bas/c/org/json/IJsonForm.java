package net.bodz.bas.c.org.json;

import net.bodz.bas.err.ParseException;

public interface IJsonForm {

    String readInStr();

    void writeInStr(String jsonStr)
            throws ParseException;

}
