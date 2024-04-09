package net.bodz.bas.c.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;

public interface ITextForm
        extends
            ITextPrintForm {

    default void parseObject(String s)
            throws ParseException {
        BufferedReader in = new BufferedReader(new StringReader(s));
        try {
            parseObject(in);
        } catch (IOException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
    }

    void parseObject(BufferedReader in)
            throws ParseException, IOException;

}
