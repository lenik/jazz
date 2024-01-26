package net.bodz.bas.fmt.rst;

import java.io.IOException;

import net.bodz.bas.c.string.StringArray;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.api.IDataCodec;
import net.bodz.bas.fmt.api.StdDataCodec;
import net.bodz.bas.t.set.FramedMarks;

public interface IRstOutput {

    IDataCodec codec = new StdDataCodec();

    FramedMarks getMarks();

    default void element(String name, IRstForm child, Object... args)
            throws IOException, FormatException {
        element(name, child, StringArray.of(args));
    }

    void element(String name, IRstForm child, String... args)
            throws IOException, FormatException;

    default void beginElement(String name, Object... args)
            throws IOException, FormatException {
        beginElement(name, StringArray.of(args));
    }

    void beginElement(String name, String... args)
            throws IOException;

    void endElement()
            throws IOException;

    default void object(String name, IRstForm child, Object... args)
            throws IOException, FormatException {
        object(name, child, StringArray.of(args));
    }

    default void object(String name, IRstForm child, String... args)
            throws IOException, FormatException {
        beginElement(name, args);
        child.writeObject(this);
        endElement();
    }

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
