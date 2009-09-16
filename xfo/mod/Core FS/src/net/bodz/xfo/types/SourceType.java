package net.bodz.xfo.types;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;

import net.bodz.bas.lang.err.ParseException;

public interface SourceType {

    /**
     * Recommend to use Apache Tika to recognize the encoding of text files.
     */
    Reader open(File file) throws IOException;

    /**
     * @return Attributes found in the script.
     */
    Map<String, String> parseAttributes(String sourceName, Reader reader) throws IOException,
            ParseException;

}
