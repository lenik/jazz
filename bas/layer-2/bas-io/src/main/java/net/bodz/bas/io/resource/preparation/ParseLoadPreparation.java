package net.bodz.bas.io.resource.preparation;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.Reader;
import java.util.List;
import java.util.Properties;

import net.bodz.bas.collection.iterator.AbstractImmediateIteratorX;
import net.bodz.bas.collection.iterator.ImmediateIteratorX;
import net.bodz.bas.collection.util.IteratorToList;
import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.jdk6compat.jdk7emul.ClassNotFoundException;
import net.bodz.bas.jdk6compat.jdk7emul.Jdk7Reflect;
import net.bodz.bas.util.exception.UnexpectedException;

public class ParseLoadPreparation
        implements IParseLoadPreparation {

    private final IStreamInputSource source;

    public ParseLoadPreparation(IStreamInputSource source) {
        if (source == null)
            throw new NullPointerException("source");
        this.source = source;
    }

    @Override
    public IParseLoadPreparation clone() {
        try {
            return (IParseLoadPreparation) super.clone();
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
        return IteratorToList.toListLimited(objectIterator(), maxItems);
    }

    public ImmediateIteratorX<Object, IOException> objectIterator()
            throws IOException {

        return new AbstractImmediateIteratorX<Object, IOException>() {

            ObjectInput in = source.newObjectInput();

            @Override
            public Object next()
                    throws IOException {
                if (isEnded())
                    return null;
                try {
                    Object object = Jdk7Reflect.readObject(in);
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
