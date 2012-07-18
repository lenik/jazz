package net.bodz.bas.io.resource.tools;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.Reader;
import java.util.List;
import java.util.Properties;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.util.iter.AbstractMitorx;
import net.bodz.bas.util.iter.Iterators;
import net.bodz.bas.util.iter.Mitorx;

public class StreamLoading
        implements IStreamLoading {

    private final IStreamInputSource source;

    public StreamLoading(IStreamInputSource source) {
        if (source == null)
            throw new NullPointerException("source");
        this.source = source;
    }

    @Override
    public IStreamLoading clone() {
        try {
            return (IStreamLoading) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
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
        return Iterators.toListLimited(objectIterator(), maxItems);
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
