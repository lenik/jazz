package net.bodz.bas.io.res.tools;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.Reader;
import java.util.List;
import java.util.Properties;

import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.io.res.IStreamInputSourceWrapper;
import net.bodz.bas.t.iterator.immed.AbstractMitorx;
import net.bodz.bas.t.iterator.immed.Mitors;
import net.bodz.bas.t.iterator.immed.Mitorx;

public class StreamLoading
        implements IStreamLoading {

    private final IStreamInputSource source;

    public StreamLoading(IStreamInputSourceWrapper wrapper) {
        this(wrapper.getInputSource());
    }

    public StreamLoading(IStreamInputSource source) {
        if (source == null)
            throw new NullPointerException("source");
        this.source = source;
    }

    @Override
    public IStreamLoading clone() {
        StreamLoading o = new StreamLoading(source);
        return o;
    }

    public Properties loadProperties()
            throws IOException {
        Reader reader = source.newReader();
        try {
            Properties properties = new Properties();
            properties.load(reader);
            return properties;
        } finally {
            reader.close();
        }
    }

    public List<Object> listObjects(int maxItems)
            throws IOException {
        return Mitors.toListLimited(objectIterator(), maxItems);
    }

    public Mitorx<Object, IOException> objectIterator()
            throws IOException {

        return new AbstractMitorx<Object, IOException>() {

            ObjectInput in = source.newObjectInput();

            @Override
            public Object _next()
                    throws IOException {
                if (isEnded())
                    return null;
                try {
                    Object object = in.readObject();
                    return object;
                } catch (ClassNotFoundException e) {
                    throw new IOException(e.getMessage(), e);
                } catch (EOFException e) {
                    in.close();
                    return end();
                }
            }

        };
    }

}
