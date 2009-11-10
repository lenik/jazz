package net.bodz.bas.io.srt;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.io.Files;

public abstract class SRT2Basic implements SRT2 {

    public final void serialize(Writer s, Object o) throws SRTException {
        try {
            serialize2(s, o);
        } catch (Exception e) {
            if (e instanceof SRTException)
                throw (SRTException) e;
            throw new SRTException(e.getMessage(), e);
        }
    }

    public final Object unserialize(Reader s) throws SRTException {
        try {
            return unserialize2(s);
        } catch (Exception e) {
            if (e instanceof SRTException)
                throw (SRTException) e;
            throw new SRTException(e.getMessage(), e);
        }
    }

    protected void serialize2(Writer s, Object o) throws Exception {
        s.write(String.valueOf(o));
    }

    protected abstract Object unserialize2(Reader s) throws Exception;

}

/** b?; */
class SRT2Byte extends SRT2Basic {
    @Override
    public Byte unserialize2(Reader s) throws IOException {
        String text = Files.readTill(s, ';');
        return Byte.parseByte(text);
    }
}

/** bb?; */
class SRT2Short extends SRT2Basic {
    @Override
    public Short unserialize2(Reader s) throws IOException {
        String text = Files.readTill(s, ';');
        return Short.parseShort(text);
    }
}

/** i?; */
class SRT2Integer extends SRT2Basic {
    @Override
    public Integer unserialize2(Reader s) throws IOException {
        String text = Files.readTill(s, ';');
        return Integer.parseInt(text);
    }
}

/** ii?; */
class SRT2Long extends SRT2Basic {
    @Override
    public Long unserialize2(Reader s) throws IOException {
        String text = Files.readTill(s, ';');
        return Long.parseLong(text);
    }
}

/** f?; */
class SRT2Float extends SRT2Basic {
    @Override
    public Float unserialize2(Reader s) throws IOException {
        String text = Files.readTill(s, ';');
        return Float.parseFloat(text);
    }
}

/** ff?; */
class SRT2Double extends SRT2Basic {
    @Override
    public Double unserialize2(Reader s) throws IOException {
        String text = Files.readTill(s, ';');
        return Double.parseDouble(text);
    }
}

/** B?; */
class SRT2Boolean extends SRT2Basic {
    public String serialize2(Object o) {
        if (Boolean.TRUE.equals(o))
            return "1"; //$NON-NLS-1$
        return "0"; //$NON-NLS-1$
    }

    @Override
    public Boolean unserialize2(Reader s) throws IOException {
        String text = Files.readTill(s, ';');
        return "1".equals(text); //$NON-NLS-1$
    }
}

/** s?; (; -> \;) */
class SRT2String extends SRT2Basic {
    @Override
    public void serialize2(Writer s, Object o) throws Exception {
        String str = String.valueOf(o);
        s.write(str.length() + ":"); //$NON-NLS-1$
        s.write("\"" + str + "\";"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    static String repeat(String pat, int count) {
        StringBuffer buffer = new StringBuffer(pat.length() * count);
        while (count-- > 0)
            buffer.append(pat);
        return buffer.toString();
    }

    @Override
    public Object unserialize2(Reader s) throws Exception {
        String slen = Files.readTill(s, ':');
        int len = Integer.parseInt(slen);
        String text = Files.readTill(s, ';');
        // assert text.charAt(pos + 1) == '\"';
        // assert text.charAt(text.length() - 1) == '\"';
        text = text.substring(1, text.length() - 1);
        if (text.length() < len)
            return s + repeat(" ", len - text.length()); //$NON-NLS-1$
        return text.substring(0, len);
    }
}

/** ic?; (; -> \;) */
class SRT2Character extends SRT2String {
    @Override
    public Character unserialize2(Reader s) throws Exception {
        String str = (String) super.unserialize2(s);
        return str.charAt(0);
    }
}

/** o?; */
class SRT2Object extends SRT2String {

    @Override
    public void serialize2(Writer s, Object o) throws Exception {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(buf);
        oos.writeObject(o);
        oos.flush();
        byte[] bytes = buf.toByteArray();
        String cdata = new String(bytes, "iso-8859-1"); //$NON-NLS-1$
        super.serialize2(s, cdata);
    }

    @Override
    public Object unserialize2(Reader s) throws Exception {
        String cdata = (String) super.unserialize2(s);
        byte[] bytes = cdata.getBytes("iso-8859-1"); //$NON-NLS-1$
        ByteArrayInputStream buf = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(buf);
        return ois.readObject();
    }

}

/** [X???; X=prefix */
class SRT2Array extends SRT2Basic {

    @Override
    public void serialize2(Writer s, Object o) throws Exception {
        Class<?> type = o.getClass().getComponentType();
        int len = Array.getLength(o);
        s.write(type.getName() + ":" + len + ":{"); //$NON-NLS-1$ //$NON-NLS-2$
        for (int i = 0; i < len; i++) {
            Object item = Array.get(o, i);
            SRTRegistry.serialize(s, item);
        }
        s.write('}');
    }

    @Override
    public Object unserialize2(Reader s) throws Exception {
        String stype = Files.readTill(s, ':');
        Class<?> type = Class.forName(stype);

        String slen = Files.readTill(s, ':');
        int len = Integer.parseInt(slen);

        Object array = Array.newInstance(type, len);
        s.read(); // {
        for (int i = 0; i < len; i++) {
            Object item = SRTRegistry.unserialize(s);
            Array.set(array, i, item);
        }
        s.read(); // }

        return array;
    }

}

/** [X????;] X=Map.class */
class SRT2Map extends SRT2Basic {

    @Override
    public void serialize2(Writer s, Object o) throws Exception {
        Map<?, ?> map = (Map<?, ?>) o;
        Class<?> type = map.getClass();
        s.write(type.getName() + ":" + map.size() + ":{"); //$NON-NLS-1$ //$NON-NLS-2$
        for (Entry<?, ?> entry : map.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            SRTRegistry.serialize(s, key);
            SRTRegistry.serialize(s, value);
        }
        s.write('}');
    }

    @Override
    public Object unserialize2(Reader s) throws Exception {
        String stype = Files.readTill(s, ':');
        Class<?> type = Class.forName(stype);

        String slen = Files.readTill(s, ':');
        int len = Integer.parseInt(slen);

        @SuppressWarnings("unchecked")
        Map<Object, Object> map = (Map<Object, Object>) type.newInstance();
        s.read(); // {
        for (int i = 0; i < len; i++) {
            Object key = SRTRegistry.unserialize(s);
            Object value = SRTRegistry.unserialize(s);
            map.put(key, value);
        }
        s.read(); // }

        return map;
    }

}
