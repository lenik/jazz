package net.bodz.bas.t.object;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import net.bodz.bas.err.ParseException;

public interface IBinaryConvertible {

    void readObject(InputStream in)
            throws ParseException;

    void writeObject(PrintStream out)
            throws IOException;

}
