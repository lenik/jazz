package net.bodz.bas.fs;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Reader;
import java.util.List;
import java.util.Properties;

import net.bodz.bas.collection.iterator.AbstractImmediateIteratorX;
import net.bodz.bas.collection.iterator.ImmediateIteratorX;
import net.bodz.bas.collection.util.IteratorToList;

public abstract class AbstractLoadToolkit
        extends AbstractReadToolkit {

    public AbstractLoadToolkit(IFile file) {
        super(file);
    }

    public Properties loadProperties()
            throws IOException {
        Reader reader = newReader();
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

            InputStream in = newInputStream();
            ObjectInputStream objIn = new ObjectInputStream(in);

            @Override
            public Object next()
                    throws IOException {
                if (isEnded())
                    return null;
                try {
                    Object object = objIn.readObject();
                    return object;
                } catch (ClassNotFoundException e) {
                    throw new IOException(e.getMessage(), e);
                } catch (EOFException e) {
                    objIn.close();
                    return end();
                }
            }

        };
    }

}
