package net.bodz.bas.i.srt;

import java.io.Reader;
import java.io.Writer;

/**
 * External SRT
 */
public interface SRT2 {

    void serialize(Writer s, Object o) throws SRTException;

    Object unserialize(Reader s) throws SRTException;

}
