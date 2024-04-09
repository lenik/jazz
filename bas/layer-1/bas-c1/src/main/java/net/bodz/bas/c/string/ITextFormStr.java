package net.bodz.bas.c.string;

import java.io.BufferedReader;
import java.io.IOException;

import net.bodz.bas.err.ParseException;

public interface ITextFormStr
        extends
            ITextForm {

    @Override
    void parseObject(String s)
            throws ParseException;

    @Override
    default void parseObject(BufferedReader in)
            throws ParseException, IOException {
        StringBuilder buf = new StringBuilder();
        int ch;
        while ((ch = in.read()) != -1)
            buf.append((char) ch);
        String s = buf.toString();
        parseObject(s);
    }

}
