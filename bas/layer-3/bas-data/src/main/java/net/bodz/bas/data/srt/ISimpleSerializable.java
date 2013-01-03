package net.bodz.bas.data.srt;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public interface ISimpleSerializable {

    void serialize(Writer s)
            throws IOException, SerializeException;

    void unserialize(Reader s)
            throws IOException, SerializeException;

}