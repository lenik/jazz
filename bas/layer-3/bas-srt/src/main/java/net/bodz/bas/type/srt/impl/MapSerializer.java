package net.bodz.bas.type.srt.impl;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.type.srt.SerializeException;
import net.bodz.bas.type.srt.SimpleSerializerRegistry;

/** [X????;] X=Map.class */
public class MapSerializer
        extends AbstractSimpleSerializer {

    @Override
    public void serialize(Writer s, Object o)
            throws IOException, SerializeException {
        Map<?, ?> map = (Map<?, ?>) o;
        Class<?> type = map.getClass();
        s.write(type.getName() + ":" + map.size() + ":{");
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
        String stype = readTill(s, ':');

        String slen = readTill(s, ':');
        int len = Integer.parseInt(slen);

        Map<Object, Object> map;
        try {
            Class<?> type = Class.forName(stype);
            map = (Map<Object, Object>) type.newInstance();
        } catch (ReflectiveOperationException e) {
            throw new SerializeException(e.getMessage(), e);
        }

        s.read(); // {
        for (int i = 0; i < len; i++) {
            Object key = SimpleSerializerRegistry.unserialize(s);
            Object value = SimpleSerializerRegistry.unserialize(s);
            map.put(key, value);
        }
        s.read(); // }

        return map;
    }

}
