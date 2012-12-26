package net.bodz.bas.data.srt.impl;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Array;

import net.bodz.bas.data.srt.SerializeException;
import net.bodz.bas.data.srt.SimpleSerializerRegistry;

/** [X???; X=prefix */
public class ArraySerializer
        extends AbstractSimpleSerializer {

    @Override
    public void serialize(Writer s, Object o)
            throws IOException, SerializeException {
        Class<?> type = o.getClass().getComponentType();
        int len = Array.getLength(o);
        s.write(type.getName() + ":" + len + ":{");
        for (int i = 0; i < len; i++) {
            Object item = Array.get(o, i);
            SimpleSerializerRegistry.serialize(s, item);
        }
        s.write('}');
    }

    @Override
    public Object unserialize(Reader s)
            throws IOException, SerializeException {
        String stype = readTill(s, ':');
        Class<?> type;
        try {
            type = Class.forName(stype);
        } catch (ClassNotFoundException e) {
            throw new SerializeException(e.getMessage(), e);
        }

        String slen = readTill(s, ':');
        int len = Integer.parseInt(slen);

        Object array = Array.newInstance(type, len);
        s.read(); // {
        for (int i = 0; i < len; i++) {
            Object item = SimpleSerializerRegistry.unserialize(s);
            Array.set(array, i, item);
        }
        s.read(); // }

        return array;
    }

}
