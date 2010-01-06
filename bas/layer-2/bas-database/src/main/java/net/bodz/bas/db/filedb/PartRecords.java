package net.bodz.bas.db.filedb;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;

import net.bodz.bas.files.PartRecordsTest;

/**
 * Preferred file name: *.maps
 * 
 * @test {@link PartRecordsTest}
 */
public class PartRecords extends _PartRecords<String, String> {

    public class PartMap extends TreeTextMap<String> {

        private static final long serialVersionUID = 7647573703380582923L;

        public String getText() {
            return get(getTextKey());
        }

        public void setText(String text) {
            put((String) getTextKey(), text);
        }

    }

    public PartRecords(File file, String encoding, int flags) {
        super(file, encoding, flags);
    }

    public PartRecords(File file, String encoding) {
        super(file, encoding);
    }

    public PartRecords(File file) {
        super(file);
    }

    public PartRecords(ResLink resLink, Charset charset, int flags) {
        super(resLink, charset, flags);
    }

    public PartRecords(ResLink resLink, Charset charset) {
        super(resLink, charset);
    }

    public PartRecords(ResLink resLink) {
        super(resLink);
    }

    public PartRecords(URL url, String encoding, int flags) {
        super(url, encoding, flags);
    }

    public PartRecords(URL url, String encoding) {
        super(url, encoding);
    }

    public PartRecords(URL url) {
        super(url);
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

    @SuppressWarnings("unchecked")
    @Override
    public DirectIterator<PartMap, IOException> iterator() {
        return (DirectIterator<PartMap, IOException>) super.iterator();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Iterable<PartMap> iterate() {
        return (Iterable<PartMap>) super.iterate();
    }

    @Override
    protected Map<String, String> newMap() {
        return new PartMap();
    }

}
