package net.bodz.bas.db.filedb;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.collection.iterator.AbstractIteratorMX;
import net.bodz.bas.collection.iterator.IteratorMX;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.io.LineReader;
import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.io.resource.builtin.LocalFileResource;

public class INIRecords
        extends MapResRecords<String, String> {

    private boolean flatten;
    private String delim = ".";
    private String existKey = "exist";

    public INIRecords(IStreamInputSource source, boolean flatten) {
        super(source);
        this.flatten = flatten;
    }

    public INIRecords(File file, String encoding) {
        this(new LocalFileResource(file, encoding), false);
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
            extends AbstractIteratorMX<Map<String, String>, IOException> {

        private LineReader lineReader;
        private String sectionName = null;

        @Override
        public Map<String, String> _next()
                throws IOException {
            if (lineReader == null)
                lineReader = source.newLineReader();

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
    public IteratorMX<? extends Map<String, String>, ? extends IOException> iterator(boolean allowOverlap)
            throws IOException {
        return new Iter();
    }

}
