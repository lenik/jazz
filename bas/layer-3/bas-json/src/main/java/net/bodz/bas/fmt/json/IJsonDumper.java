package net.bodz.bas.fmt.json;

import java.io.IOException;

import net.bodz.bas.err.FormatException;

public interface IJsonDumper {

    void dump(Object obj, boolean enclosed)
            throws IOException, FormatException;

}
