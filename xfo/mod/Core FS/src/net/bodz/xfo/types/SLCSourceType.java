package net.bodz.xfo.types;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.TreeTextMap;
import net.bodz.xfo.core._SourceType;

public abstract class SLCSourceType extends _SourceType {

    public SLCSourceType(Charset charset) {
        super(charset);
    }

    private TextMap<String> map;

    protected void putAttribute(String id, String text) {
        map.put(id, text);
    }

    @Override
    public synchronized TextMap<String> parseAttributes(String sourceName, Reader reader)
            throws IOException, ParseException {
        BufferedReader lineReader = new BufferedReader(reader);
        map = new TreeTextMap<String>();
        String line;
        int lineNo = 0;
        try {
            while ((line = lineReader.readLine()) != null) {
                lineNo++;
                parseLine(line);
            }
        } catch (ParseException e) {
            ParseException e2 = new ParseException(e.getMessage(), e);
            e2.setLocation(sourceName, lineNo);
            throw e2;
        }
        return map;
    }

    protected abstract void parseLine(String line) throws IOException, ParseException;

}
