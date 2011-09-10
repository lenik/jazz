package net.bodz.bas.db.filedb;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.collection.iterator.IteratorMX;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.io.resource.IStreamInputSource;

/**
 * Preferred file name: *.maps
 */
public class PartRecords
        extends _PartRecords<String, String> {

    public class PartMap
            extends TreeMap<String, String> {

        private static final long serialVersionUID = 7647573703380582923L;

        public String getText() {
            return get(getTextKey());
        }

        public void setText(String text) {
            put(getTextKey(), text);
        }

    }

    public PartRecords(IStreamInputSource source, int flags) {
        super(source, flags);
    }

    public PartRecords(IStreamInputSource source) {
        super(source);
    }

    public PartRecords(File file, String encoding) {
        super(file, encoding);
    }

    public PartRecords(File file, String encoding, int flags) {
        super(file, encoding, flags);
    }

    public PartRecords(URL url, String encoding) {
        super(url, encoding);
    }

    public PartRecords(URL url, String encoding, int flags) {
        super(url, encoding, flags);
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
    public IteratorMX<? extends PartMap, ? extends IOException> iterator(boolean allowOverlap)
            throws IOException {
        return (IteratorMX<? extends PartMap, ? extends IOException>) super.iterator(allowOverlap);
    }

    @Override
    protected Map<String, String> newMap() {
        return new PartMap();
    }

}
