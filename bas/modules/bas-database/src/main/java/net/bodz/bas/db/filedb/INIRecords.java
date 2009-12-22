package net.bodz.bas.db.filedb;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.NoSuchElementException;

import net.bodz.bas.commons.collection.TextMap;
import net.bodz.bas.commons.exceptions.ParseException;
import net.bodz.bas.commons.iterators.DirectIterator;
import net.bodz.bas.commons.iterators._DirectIterator;
import net.bodz.bas.commons.typealiases.TreeTextMap;
import net.bodz.bas.files.INIRecordsTest;
import net.bodz.bas.io.FileResLink;
import net.bodz.bas.io.LineReader;
import net.bodz.bas.io.ResLink;
import net.bodz.bas.nls.TypesNLS;

/**
 * @test {@link INIRecordsTest}
 */
public class INIRecords extends MapResRecords<String, String> {

    private boolean flatten;
    private String delim = "."; //$NON-NLS-1$
    private String existKey = "exist"; //$NON-NLS-1$

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
    protected String parseKey(String key) throws ParseException {
        return key;
    }

    @Override
    protected String parseValue(String value) throws ParseException {
        return value;
    }

    @Override
    protected TextMap<String> newMap() {
        return new TreeTextMap<String>();
    }

    class Iter extends _DirectIterator<TextMap<String>, IOException> {

        private LineReader lineReader;
        private boolean end;
        private String sectionName = null;
        private TextMap<String> map;

        @Override
        public boolean next() throws IOException {
            if (end)
                return false;
            if (lineReader == null) {
                Reader reader = resLink.openReader(charset);
                lineReader = new LineReader(reader);
            }
            map = newMap();
            String line;
            while ((line = lineReader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) //$NON-NLS-1$
                    continue;
                if (line.startsWith("[")) { //$NON-NLS-1$
                    assert line.endsWith("]"); //$NON-NLS-1$
                    String futureSection = line.substring(1, line.length() - 1).trim();
                    if (flatten) {
                        map.put(futureSection + delim + existKey, "true");
                        sectionName = futureSection;
                        continue;
                    } else {
                        if (sectionName != null)
                            map.put("_section", sectionName); //$NON-NLS-1$
                        sectionName = futureSection;
                        return true;
                    }
                }
                int eq = line.indexOf('=');
                if (eq == -1) {
                    // BOM test.
                    String mesg = TypesNLS.getString("INIFile.invalidEntry") + line; //$NON-NLS-1$
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
                map.put("_section", sectionName); //$NON-NLS-1$
            end = true;
            return true;
        }

        @Override
        public TextMap<String> get() throws NoSuchElementException {
            return map;
        }

    }

    @Override
    public DirectIterator<TextMap<String>, IOException> iterator() {
        return new Iter();
    }

}
