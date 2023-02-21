package net.bodz.bas.fmt.rst;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.api.IDataCodec;
import net.bodz.bas.fmt.api.StdDataCodec;
import net.bodz.bas.t.set.FramedMarks;

public interface IRstOutput {

    IDataCodec codec = new StdDataCodec();

    FramedMarks getMarks();

    void element(String name, IRstForm child, String... args)
            throws IOException, FormatException;

    void beginElement(String name, String... args)
            throws IOException;

    void endElement()
            throws IOException;

    void attributeRaw(String name, String rawData)
            throws IOException;

    /**
     * Always escaped.
     */
    void attribute(String name, Object value)
            throws IOException;

    default void attributeNotNull(String name, Object value)
            throws IOException {
        if (value != null)
            attribute(name, value);
    }

    void attributeQuoted(String name, Object value)
            throws IOException;

}
