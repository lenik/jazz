package net.bodz.bas.srt.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.Writer;

import net.bodz.bas.srt.SerializeException;

/** o?; */
public class ObjectSerializer
        extends StringSerializer {

    @Override
    public void serialize(Writer s, Object o)
            throws IOException, SerializeException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(buf);
        oos.writeObject(o);
        oos.flush();
        byte[] bytes = buf.toByteArray();
        String cdata = new String(bytes, "iso-8859-1");
        super.serialize(s, cdata);
    }

    @Override
    public Object unserialize(Reader s)
            throws IOException, SerializeException {
        String cdata = (String) super.unserialize(s);
        byte[] bytes = cdata.getBytes("iso-8859-1");
        ByteArrayInputStream buf = new ByteArrayInputStream(bytes);
        ObjectInputStream in = new ObjectInputStream(buf);
        try {
            return in.readObject();
        } catch (ClassNotFoundException e) {
            throw new SerializeException(e.getMessage(), e);
        }
    }

}
