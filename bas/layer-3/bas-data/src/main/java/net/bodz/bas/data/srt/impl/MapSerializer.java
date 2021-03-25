package net.bodz.bas.data.srt.impl;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.data.srt.SerializeException;
import net.bodz.bas.data.srt.SimpleSerializerRegistry;

/** [X????;] X=Map.class */
public class MapSerializer
        extends AbstractSimpleSerializer {

    @Override
    public void serialize(Writer s, Object o)
            throws IOException, SerializeException {
        Class<?> mapType = o.getClass();
        if (!(o instanceof Map))
            throw new IllegalArgumentException("Not a map: " + mapType);
        Map<?, ?> map = (Map<?, ?>) o;
        s.write(mapType.getName() + ":" + map.size() + ":{");
        for (Entry<?, ?> entry : map.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            SimpleSerializerRegistry.serialize(s, key);
            SimpleSerializerRegistry.serialize(s, value);
        }
        s.write('}');
    }

    @Override
    public Object unserialize(Reader s)
            throws IOException, SerializeException {
        String mapTypeName = readTill(s, ':');

        String mapSize = readTill(s, ':');
        int size = Integer.parseInt(mapSize);

        Map<Object, Object> map;
        try {
            Class<?> mapType = Class.forName(mapTypeName);
            if (!Map.class.isAssignableFrom(mapType))
                throw new SerializeException("Not a map type: " + mapType);
            @SuppressWarnings("unchecked")
            Map<Object, Object> instance = (Map<Object, Object>) mapType.newInstance();
            map = instance;
        } catch (ReflectiveOperationException e) {
            throw new SerializeException(e.getMessage(), e);
        }

        s.read(); // {
        for (int i = 0; i < size; i++) {
            Object key = SimpleSerializerRegistry.unserialize(s);
            Object value = SimpleSerializerRegistry.unserialize(s);
            map.put(key, value);
        }
        s.read(); // }

        return map;
    }

}
