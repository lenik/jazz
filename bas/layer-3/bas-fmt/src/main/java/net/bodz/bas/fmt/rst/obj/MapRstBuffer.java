package net.bodz.bas.fmt.rst.obj;

import java.util.Map;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.api.ElementHandlerException;
import net.bodz.bas.fmt.rst.AbstractRstHandler;
import net.bodz.bas.fmt.rst.IRstHandler;
import net.bodz.bas.fmt.rst.IRstSerializable;
import net.bodz.bas.typer.Typers;
import net.bodz.bas.typer.std.IParser;

public class MapRstBuffer<K, V>
        extends AbstractRstHandler {

    Map<K, V> map;
    Class<K> keyType;
    Class<V> valueType;

    IParser<K> keyParser;
    IParser<V> valueParser;

    public MapRstBuffer(Map<K, V> map, Class<K> keyType, Class<V> valueType) {
        this.map = map;
        this.keyType = keyType;
        this.valueType = valueType;

        keyParser = Typers.getTyper(keyType, IParser.class);
        if (keyParser == null)
            throw new IllegalArgumentException("No parser for " + keyType);

        valueParser = Typers.getTyper(valueType, IParser.class);

        boolean rstValue = IRstSerializable.class.isAssignableFrom(valueType);
        if (!rstValue)
            throw new NotImplementedException("Non-rst value isn't supported yet: " + valueType);
    }

    @Override
    public boolean attribute(String name, String data)
            throws ParseException, ElementHandlerException {
        K key = keyParser.parse(name);
        V value = valueParser.parse(data);
        map.put(key, value);
        return true;
    }

    @Override
    public IRstHandler beginChild(String name, String[] args)
            throws ParseException, ElementHandlerException {
        K key = keyParser.parse(name);
        V value = map.get(key);
        if (value == null) {
            value = create(key, args);
            map.put(key, value);
        }
        IRstSerializable rstValue = (IRstSerializable) value;
        return rstValue.getElementHandler();
    }

    protected V create(K key, String[] args) {
        try {
            V value = valueType.newInstance();
            return value;
        } catch (InstantiationException e) {
            throw new ElementHandlerException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new ElementHandlerException(e.getMessage(), e);
        }
    }

}
