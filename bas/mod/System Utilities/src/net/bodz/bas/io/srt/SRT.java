package net.bodz.bas.io.srt;

import java.io.Reader;
import java.io.Writer;

public interface SRT {

    void serialize(Writer s) throws SRTException;

    void unserialize(Reader s) throws SRTException;

}
