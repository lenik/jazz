package net.bodz.bas.fmt.records;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.t.iterator.immed.Mitorx;

/**
 * Preferred file extension: *.txm
 */
public class MultiTextMaps
        extends AbstractMultiMapsFormat<String, String> {

    public static class PartMap
            extends TreeMap<String, String> {

        private static final long serialVersionUID = 1L;

        final String textKey;

        public PartMap(String textKey) {
            this.textKey = textKey;
        }

        public String getText() {
            return get(textKey);
        }

        public void setText(String text) {
            put(textKey, text);
        }

    }

    public MultiTextMaps(IStreamInputSource source, int flags) {
        super(source, flags);
    }

    public MultiTextMaps(IStreamInputSource source) {
        super(source);
    }

    public MultiTextMaps(File file, String encoding) {
        super(file, encoding);
    }

    public MultiTextMaps(File file, String encoding, int flags) {
        super(file, encoding, flags);
    }

    public MultiTextMaps(URL url, String encoding) {
        super(url, encoding);
    }

    public MultiTextMaps(URL url, String encoding, int flags) {
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
    public Mitorx<? extends PartMap, ? extends IOException> iterator(boolean allowOverlap) {
        return (Mitorx<? extends PartMap, ? extends IOException>) super.iterator(allowOverlap);
    }

    @Override
    protected Map<String, String> newMap() {
        return new PartMap(getTextKey());
    }

}
