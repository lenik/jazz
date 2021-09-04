package net.bodz.bas.fmt.json;

import java.io.IOException;

import net.bodz.bas.err.FormatException;

public interface IJsonDumper {

    void dump(Object obj)
            throws IOException, FormatException;

    void dumpBoxed(Object obj)
            throws IOException, FormatException;

}
