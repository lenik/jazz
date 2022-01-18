package net.bodz.bas.c.org.json;

import net.bodz.bas.err.ParseException;

public interface IJsonStrForm {

    String toJsonStr();

    void fromJsonStr(String jsonStr)
            throws ParseException;

}
