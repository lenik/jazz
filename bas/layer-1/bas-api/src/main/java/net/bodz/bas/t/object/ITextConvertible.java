package net.bodz.bas.t.object;

import java.io.IOException;
import java.io.Writer;

import net.bodz.bas.err.ParseException;

public interface ITextConvertible {

    void readObject(String s)
            throws ParseException;

    void writeObject(Writer out)
            throws IOException;

}
