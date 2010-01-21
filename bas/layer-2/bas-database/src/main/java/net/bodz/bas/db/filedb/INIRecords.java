package net.bodz.bas.db.filedb;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.collection.iterator.AbstractImmediateIteratorX;
import net.bodz.bas.collection.iterator.ImmediateIteratorX;
import net.bodz.bas.exceptions.ParseException;
import net.bodz.bas.files.INIRecordsTest;
import net.bodz.bas.io.LineReader;

/**
 * @test {@link INIRecordsTest}
 */
public class INIRecords
        extends MapResRecords<String, String> {

    private boolean flatten;
    private String delim = ".";
    private String existKey = "exist";

    public INIRecords(ResLink resLink, Charset charset, boolean flatten) {
        super(resLink, charset);
        this.flatten = flatten;
    }

    public INIRecords(ResLink resLink, boolean flatten) {
        this(resLink, null, flatten);
    }

    public INIRecords(File file, String encoding) {
        this(new FileResLink(file), encoding == null ? null : Charset.forName(encoding), false);
    }

    public INIRecords(File file) {
        this(file, null);
    }

    @Override
    protected Class<? extends String> getKeyClass() {
        return String.class;
    }

    @Override
    protected Class<? extends String> getValueClass() {
        return String.class;
    }

    @Override
    protected String parseKey(String key)
            throws ParseException {
        return key;
    }

    @Override
    protected String parseValue(String value)
            throws ParseException {
        return value;
    }

    @Override
    protected Map<String, String> newMap() {
        return new TreeMap<String, String>();
    }

    class Iter
            extends AbstractImmediateIteratorX<Map<String, String>, IOException> {

        private LineReader lineReader;
        private String sectionName = null;

        @Override
        public Map<String, String> next()
                throws IOException {
            if (lineReader == null) {
                Reader reader = resLink.openReader(charset);
                lineReader = new LineReader(reader);
            }

            Map<String, String> map = newMap();
            String line;
            while ((line = lineReader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#"))
                    continue;
                if (line.startsWith("[")) {
                    assert line.endsWith("]");
                    String futureSection = line.substring(1, line.length() - 1).trim();
                    if (flatten) {
                        map.put(futureSection + delim + existKey, "true");
                        sectionName = futureSection;
                        continue;
                    } else {
                        if (sectionName != null)
                            map.put("_section", sectionName);
                        sectionName = futureSection;
                        return map;
                    }
                }
                int eq = line.indexOf('=');
                if (eq == -1) {
                    // BOM test.
                    String mesg = "invalid entry: " + line;
                    System.err.println(mesg);
                    // throw new ParseException(mesg);
                    continue;
                }
                String key = line.substring(0, eq).trim();
                if (flatten && sectionName != null)
                    key = sectionName + delim + key;
                String value = line.substring(eq + 1).trim();
                map.put(key, value);
            } // lines
            if (!flatten && sectionName != null)
                map.put("_section", sectionName);
            return end();
        }

    }

    @Override
    public ImmediateIteratorX<? extends Map<String, String>, ? extends IOException> iterator(boolean allowOverlap)
            throws IOException {
        return new Iter();
    }

}
