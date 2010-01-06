package net.bodz.bas.db.filedb;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

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
            keyParser = TypeParsers.guess(getKeyClass(), "KeyClass"); 
        Object k = keyParser.parse(key);
        return keyClass.cast(k);
    }

    protected V parseValue(String value) throws ParseException {
        if (valueParser == null)
            valueParser = TypeParsers.guess(getValueClass(), "ValueClass"); 
        Object v = valueParser.parse(value);
        return valueClass.cast(v);
    }

    protected Map<K, V> newMap() {
        return new HashMap<K, V>();
    }

}
