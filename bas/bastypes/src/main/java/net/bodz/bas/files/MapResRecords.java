package net.bodz.bas.files;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.io.ResLink;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.types.TypeParser;
import net.bodz.bas.types.TypeParsers;

public abstract class MapResRecords<K, V> extends _ResRecords<Map<K, V>> {

    protected Class<? extends K> keyClass;
    protected Class<? extends V> valueClass;
    protected TypeParser keyParser;
    protected TypeParser valueParser;

    public MapResRecords(ResLink resLink) {
        this(resLink, null);
    }

    public MapResRecords(ResLink resLink, Charset charset) {
        super(resLink, charset);
        keyClass = getKeyClass();
        valueClass = getValueClass();
    }

    protected abstract Class<? extends K> getKeyClass();

    protected abstract Class<? extends V> getValueClass();

    protected K parseKey(String key) throws ParseException {
        if (keyParser == null)
            keyParser = TypeParsers.guess(getKeyClass(), "KeyClass"); //$NON-NLS-1$
        Object k = keyParser.parse(key);
        return keyClass.cast(k);
    }

    protected V parseValue(String value) throws ParseException {
        if (valueParser == null)
            valueParser = TypeParsers.guess(getValueClass(), "ValueClass"); //$NON-NLS-1$
        Object v = valueParser.parse(value);
        return valueClass.cast(v);
    }

    protected Map<K, V> newMap() {
        return new HashMap<K, V>();
    }

}
