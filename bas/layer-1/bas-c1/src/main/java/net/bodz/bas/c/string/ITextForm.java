package net.bodz.bas.c.string;

import java.io.BufferedReader;
import java.io.IOException;

import net.bodz.bas.err.ParseException;

public interface ITextForm
        extends
            ITextPrintForm {

    void parseObject(BufferedReader in)
            throws ParseException, IOException;

}
