package net.bodz.bas.db.filedb;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.typer.Typers;
import net.bodz.bas.typer.std.IParser;

public abstract class AbstractSeqMaps<K, V>
        extends AbstractSeqRecords<Map<K, V>>
        implements ISeqMaps<K, V> {

    protected Class<? extends K> keyClass;
    protected Class<? extends V> valueClass;
    protected IParser<K> keyParser;
    protected IParser<V> valueParser;

    public AbstractSeqMaps(IStreamInputSource source) {
        super(source);
        keyClass = getKeyClass();
        valueClass = getValueClass();
    }

    protected abstract Class<? extends K> getKeyClass();

    protected abstract Class<? extends V> getValueClass();

    protected K parseKey(String key)
            throws ParseException {
        if (keyParser == null)
            keyParser = Typers.getTyper(getKeyClass(), IParser.class);
        Object k = keyParser.parse(key);
        return keyClass.cast(k);
    }

    protected V parseValue(String value)
            throws ParseException {
        if (valueParser == null)
            valueParser = Typers.getTyper(getValueClass(), IParser.class);
        Object v = valueParser.parse(value);
        return valueClass.cast(v);
    }

    protected Map<K, V> newMap() {
        return new HashMap<K, V>();
    }

}
